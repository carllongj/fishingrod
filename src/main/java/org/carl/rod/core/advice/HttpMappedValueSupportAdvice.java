package org.carl.rod.core.advice;

import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.TaskCtl;
import org.carl.rod.config.http.HttpMappedValueTaskInputHandler;
import org.carl.rod.config.http.JsonFormatOutputHandler;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.task.TaskPostProcessor;

/**
 * @author longjie
 * 2021/5/18
 */
public class HttpMappedValueSupportAdvice implements TaskPostProcessor {

	@Override
	public Task postProcess(Task task, TaskConfiguration taskConfiguration) {
		if (task instanceof TaskCtl) {
			setOutputConfig((TaskCtl) task, taskConfiguration);

			if (null != taskConfiguration.getSelector()) {
				HttpMappedValueTaskInputHandler taskInputHandler = new HttpMappedValueTaskInputHandler();
				taskInputHandler.setSelectors(taskConfiguration.getSelector());
				((TaskCtl) task).setTaskInput(taskInputHandler);
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
