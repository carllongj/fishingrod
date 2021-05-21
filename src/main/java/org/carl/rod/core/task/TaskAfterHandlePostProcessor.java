package org.carl.rod.core.task;

import org.carl.rod.config.task.Task;

/**
 * @author longjie
 * 2021/5/17
 */
public interface TaskAfterHandlePostProcessor extends TaskPostProcessor {

	/**
	 * 后置操作
	 *
	 * @param task        当前执行的任务
	 * @param taskFactory 任务工厂
	 */
	void afterHandle(Task task, HttpTaskFactory taskFactory);
}
