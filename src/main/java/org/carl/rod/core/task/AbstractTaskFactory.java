package org.carl.rod.core.task;

import org.carl.rod.config.base.CommonConfiguration;
import org.carl.rod.config.base.DefaultConfiguration;
import org.carl.rod.config.base.HttpRequestConfiguration;
import org.carl.rod.config.base.OutputConfiguration;
import org.carl.rod.config.base.RodBaseConfiguration;
import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.Document;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.http.doc.DocumentCreator;
import org.carl.rod.core.http.handlers.HttpFinishedHandler;
import org.carl.rod.core.name.TaskNameGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/13
 */
public abstract class AbstractTaskFactory implements TaskFactory {

	/**
	 * 任务处理
	 */
	private List<TaskPostProcessor> taskPostProcessors;

	/**
	 * 任务回调器
	 */
	private List<HttpFinishedHandler> finishedHandlers;

	/**
	 * 文档创建器
	 */
	private List<DocumentCreator> documentCreators;

	/**
	 * 名称生成器
	 */
	private TaskNameGenerator taskNameGenerator;

	public AbstractTaskFactory() {
		this.taskPostProcessors = new ArrayList<>();
		this.documentCreators = new ArrayList<>();
		this.finishedHandlers = new ArrayList<>();
	}

	@Override
	public void addTaskPostProcessor(TaskPostProcessor processor) {
		this.taskPostProcessors.add(processor);
	}

	@Override
	public List<TaskPostProcessor> getTaskPostProcessor() {
		return this.taskPostProcessors;
	}

	@Override
	public void setTaskNameGenerator(TaskNameGenerator taskNameGenerator) {
		this.taskNameGenerator = taskNameGenerator;
	}

	@Override
	public TaskNameGenerator getTaskNameGenerator() {
		return taskNameGenerator;
	}

	@Override
	public void addFinishedHandler(HttpFinishedHandler finishedHandler) {
		this.finishedHandlers.add(finishedHandler);
	}

	@Override
	public List<HttpFinishedHandler> getHttpFinishedHandler() {
		return finishedHandlers;
	}

	@Override
	public void addDocumentCreator(DocumentCreator documentCreator) {
		this.documentCreators.add(documentCreator);
	}

	@Override
	public Document createDocument(Object source) {
		for (DocumentCreator documentCreator : this.documentCreators) {
			if (documentCreator.isSupport(source)) {
				return documentCreator.createDocument(source);
			}
		}
		return null;
	}

	@Override
	public List<Task> createTask(RodBaseConfiguration configuration) {
		if (null == configuration) {
			return Collections.emptyList();
		}

		return doCreateTasks(configuration.getRod());
	}

	private List<Task> doCreateTasks(DefaultConfiguration rod) {
		List<TaskConfiguration> taskInfo = rod.getTaskInfo();

		// 若当前配置项中未进行设置任务信息
		if (Objects.isNull(taskInfo) || taskInfo.isEmpty()) {
			return Collections.emptyList();
		}

		List<Task> taskList = new LinkedList<>();

		// 遍历所有的任务
		for (TaskConfiguration taskConfiguration : taskInfo) {
			// 合并参数配置项
			if (null != rod.getCommon()) {
				generateTaskConfiguration(rod.getCommon(), taskConfiguration);
			}

			for (int i = 0; i < taskPostProcessors.size(); i++) {

				TaskPostProcessor processor = taskPostProcessors.get(i);
				if (processor instanceof TaskCreatePostProcessor) {
					((TaskCreatePostProcessor) processor).beforeCreateTask(this, taskConfiguration);
				}
			}

			// 创建任务
			Task task = doCreateTask(taskConfiguration);

			//任务创建完成后的后置增强
			for (int i = 0; i < taskPostProcessors.size(); i++) {
				task = taskPostProcessors.get(i).postProcess(task, taskConfiguration);
			}

			// 添加任务
			taskList.add(task);
		}
		return taskList;
	}

	/**
	 * 生成所有的任务配置信息,包括任务名称相关信息
	 *
	 * @param common            公共配置信息
	 * @param taskConfiguration 任务配置信息
	 */
	protected void generateTaskConfiguration(CommonConfiguration common, TaskConfiguration taskConfiguration) {
		setTaskNameIfNecessary(taskConfiguration);
		mergeOutputConfigurationIfNecessary(common, taskConfiguration);
		mergeHttpConfigurationIfNecessary(common, taskConfiguration);
	}

	/**
	 * 检查是否需要进行设置任务名称
	 *
	 * @param taskConfiguration 任务配置信息
	 */
	private void setTaskNameIfNecessary(TaskConfiguration taskConfiguration) {
		if (Objects.isNull(taskConfiguration.getTaskName())) {
			taskConfiguration.setTaskName(this.getTaskNameGenerator().generateTaskName());
		}
	}


	/**
	 * 合并请求参数的相同配置项
	 *
	 * @param common            通用配置项
	 * @param taskConfiguration 任务配置项
	 */
	private void mergeOutputConfigurationIfNecessary(CommonConfiguration common, TaskConfiguration taskConfiguration) {
		// 普通的任务文件未进行设置
		if (null == taskConfiguration.getOutput()) {
			if (null == common.getOutput()) {
				taskConfiguration.setOutput(OutputConfiguration.DEFAULT_OUTPUT_CONFIGURATION);
			} else {
				taskConfiguration.setOutput(common.getOutput());
			}
		}

		//设置当前的输出文件名称
		taskConfiguration.getOutput().setFileName(taskConfiguration.getTaskName());
	}

	/**
	 * 合并相同的http参数配置项
	 *
	 * @param common            通用配置项
	 * @param taskConfiguration 任务配置信息
	 */
	private void mergeHttpConfigurationIfNecessary(CommonConfiguration common, TaskConfiguration taskConfiguration) {
		// 合并相同的参数配置项
		if (null == taskConfiguration.getHttpConfig()) {
			taskConfiguration.setHttpConfig(new HttpRequestConfiguration());
		}
		taskConfiguration.getHttpConfig().addHttpRequestConfiguration(common.getHttp());
	}

	/**
	 * 创建任务
	 *
	 * @param taskConfig 执行创建任务
	 * @return 返回创建完成的任务
	 */
	protected abstract Task doCreateTask(TaskConfiguration taskConfig);
}
