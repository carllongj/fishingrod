package org.carl.rod.config.task;

/**
 * @author longjie
 * 2021/5/19
 */
public interface TaskAware {

	/**
	 * 用以设置当前的Task信息
	 *
	 * @param task 设置对应的Task信息
	 */
	void setTask(Task task);
}
