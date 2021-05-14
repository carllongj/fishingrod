package org.carl.rod.core.task;

import org.carl.rod.config.base.DefaultConfiguration;
import org.carl.rod.config.base.HttpRequestConfiguration;
import org.carl.rod.config.base.RodBaseConfiguration;
import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.task.Task;
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
	 * 名称生成器
	 */
	private TaskNameGenerator taskNameGenerator;

	public AbstractTaskFactory() {
		this.taskPostProcessors = new ArrayList<>();
	}

	@Override
	public void addTaskPostProcessor(TaskPostProcessor processor) {
		this.taskPostProcessors.add(processor);
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
			for (int i = 0; i < taskPostProcessors.size(); i++) {
				// 合并参数配置项
				mergeConfiguration(rod.getCommon(), taskConfiguration);

				TaskPostProcessor processor = taskPostProcessors.get(i);

				if (processor instanceof TaskCreatePostProcessor) {
					((TaskCreatePostProcessor) processor).beforeCreateTask(taskConfiguration);
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
	 * 合并请求参数的相同配置项
	 *
	 * @param common            通用配置项
	 * @param taskConfiguration 任务配置项
	 */
	protected void mergeConfiguration(HttpRequestConfiguration common, TaskConfiguration taskConfiguration) {
		// 合并相同的参数配置项
		taskConfiguration.getHttpConfig().addHttpRequestConfiguration(common);
	}

	/**
	 * 创建任务
	 *
	 * @param taskConfig 执行创建任务
	 * @return 返回创建完成的任务
	 */
	protected abstract Task doCreateTask(TaskConfiguration taskConfig);
}