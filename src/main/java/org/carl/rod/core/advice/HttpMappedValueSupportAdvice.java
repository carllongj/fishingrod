package org.carl.rod.core.advice;

import org.carl.rod.config.base.OutputConfiguration;
import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.TaskCtl;
import org.carl.rod.config.http.FileOutputFormatHandler;
import org.carl.rod.config.http.HttpMappedValueTaskInputHandler;
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
			if (null != taskConfiguration.getOutput()) {
				OutputConfiguration outputConfiguration = taskConfiguration.getOutput();
				((TaskCtl) task).addTaskOutputHandler(new FileOutputFormatHandler(outputConfiguration.getPath(),
					taskConfiguration.getTaskName(), outputConfiguration.getSuffix()));
			}

			if (null != taskConfiguration.getSelector()) {
				HttpMappedValueTaskInputHandler taskInputHandler = new HttpMappedValueTaskInputHandler();
				taskInputHandler.setSelectors(taskConfiguration.getSelector());
				((TaskCtl) task).setTaskInput(taskInputHandler);
			}
		}
		return task;
	}
}
