package org.carl.rod.config.task;

/**
 * @author longjie
 * 2021/5/21
 */
public interface HierarchicalTask extends Task {

	/**
	 * 获取父级别的Task,
	 *
	 * @return 若当前的Task为单一任务, 则返回为 <code>null</code>,若为阶段性任务,则返回为根任务
	 */
	Task getParent();
}
