package org.carl.rod.core.advice;

import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.TaskCtl;
import org.carl.rod.config.ctl.TaskOutputHandler;
import org.carl.rod.config.http.HttpMappedValueTaskInputHandler;
import org.carl.rod.config.http.JsonFormatOutputHandler;
import org.carl.rod.config.http.PageUrlFileOutputFormatHandler;
import org.carl.rod.config.page.HttpPageRequestTask;
import org.carl.rod.config.task.HttpRequestTask;
import org.carl.rod.config.task.StagedTask;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.task.TaskPostProcessor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/18
 */
public class HttpMappedValueSupportAdvice implements TaskPostProcessor {

	private static final String PAGE_URL_KEY = "URL";

	@Override
	public Task postProcess(Task task, TaskConfiguration taskConfiguration) {

		if (task instanceof TaskCtl) {
			setOutputConfig((TaskCtl) task, taskConfiguration);

			if (null != taskConfiguration.getSelectors()) {
				HttpMappedValueTaskInputHandler taskInputHandler = new HttpMappedValueTaskInputHandler();
				taskInputHandler.setSelectors(taskConfiguration.getSelectors());
				((TaskCtl) task).setTaskInput(taskInputHandler);
			}

			// 对子任务进行处理
			if (task instanceof StagedTask) {
				for (Task childTask : ((StagedTask) task).getStagedTasks()) {
					if (childTask instanceof TaskCtl) {
						if (childTask instanceof HttpPageRequestTask) {
							HttpMappedValueTaskInputHandler handler = new HttpMappedValueTaskInputHandler();
							Map<String, List<String>> selectorMap =
								Collections.singletonMap(PAGE_URL_KEY, taskConfiguration.getUrlsProvider().getHttpUrl().getPageConfig().getUrlSelector());
							handler.setSelectors(selectorMap);
							((TaskCtl) childTask).setTaskInput(handler);

							((TaskCtl) childTask).addTaskOutputHandler(new PageUrlFileOutputFormatHandler());
						}

						if (childTask instanceof HttpRequestTask) {
							TaskCtl targetTask = (TaskCtl) task;
							if (Objects.isNull(((TaskCtl) childTask).getTaskInput())) {
								((TaskCtl) childTask).setTaskInput(targetTask.getTaskInput());
							}

							if (Objects.isNull(((TaskCtl) childTask).getTaskOutputHandler()) && !targetTask.getTaskOutputHandler().isEmpty()) {
								for (TaskOutputHandler taskOutputHandler : targetTask.getTaskOutputHandler()) {
									((TaskCtl) childTask).addTaskOutputHandler(taskOutputHandler);
								}
							}
						}
					}
				}
			}
		}
		return task;
	}

	/**
	 * 设置输出配置信息
	 *
	 * @param task              目标任务
	 * @param taskConfiguration 任务配置信息
	 */
	private void setOutputConfig(TaskCtl task, TaskConfiguration taskConfiguration) {
		if (null != taskConfiguration.getOutput()) {
			task.addTaskOutputHandler(new JsonFormatOutputHandler());
		}
	}
}
