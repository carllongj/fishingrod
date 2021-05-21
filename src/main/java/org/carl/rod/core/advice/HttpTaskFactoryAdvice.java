package org.carl.rod.core.advice;

import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.TaskFactoryAware;
import org.carl.rod.config.task.StagedTask;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.task.HttpTaskFactory;
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
	private HttpTaskFactory taskFactory;

	public HttpTaskFactoryAdvice(HttpTaskFactory taskFactory) {
		this.taskFactory = taskFactory;
	}

	@Override
	public Task postProcess(Task task, TaskConfiguration taskConfiguration) {
		if (task instanceof StagedTask) {
			for (Task childTask : ((StagedTask) task).getStagedTasks()) {
				if (childTask instanceof TaskFactoryAware) {
					((TaskFactoryAware) childTask).setTaskFactory(taskFactory);
				}
			}
		} else if (task instanceof TaskFactoryAware) {
			((TaskFactoryAware) task).setTaskFactory(taskFactory);
		}
		return task;
	}
}
