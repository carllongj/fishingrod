package org.carl.rod.config.task;

/**
 * 标识当前为Task
 *
 * @author longjie
 * 2021/5/13
 */
public interface Task {
	/**
	 * 获取当前任务的名称,若任务未指定名称,将生成一个唯一的标识符名称
	 *
	 * @return 返回当前任务的标识符名称
	 */
	String getTaskName();

	/**
	 * 设置当前的任务名称
	 *
	 * @param taskName 设置任务名称
	 */
	void setTaskName(String taskName);

	/**
	 * 执行当前的任务,返回最终的执行结果
	 */
	boolean executeTask();
}
