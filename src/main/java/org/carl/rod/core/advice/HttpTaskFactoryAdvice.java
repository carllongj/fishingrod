package org.carl.rod.core.advice;

import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.TaskFactoryAware;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.task.TaskFactory;
import org.carl.rod.core.task.TaskPostProcessor;

/**
 * 设置 TaskFactory
 *
 * @author longjie
 * 2021/5/17
 */
public class HttpTaskFactoryAdvice implements TaskPostProcessor {

	/**
	 * 设置任务工厂
	 */
	private TaskFactory taskFactory;

	public HttpTaskFactoryAdvice(TaskFactory taskFactory) {
		this.taskFactory = taskFactory;
	}

	@Override
	public Task postProcess(Task task, TaskConfiguration taskConfiguration) {
		if (task instanceof TaskFactoryAware) {
			((TaskFactoryAware) task).setTaskFactory(taskFactory);
		}
		return task;
	}
}
