package org.carl.rod.core.task;

import org.carl.rod.config.task.Task;

/**
 * @author longjie
 * 2021/5/17
 */
public interface TaskAfterHandlePostProcessor extends TaskPostProcessor {

	void afterHandle(Task task);
}
