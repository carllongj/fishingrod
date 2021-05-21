package org.carl.rod.config.page;

import org.carl.rod.config.task.HierarchicalTask;
import org.carl.rod.config.task.HttpRequestTask;
import org.carl.rod.config.task.Task;

/**
 * @author longjie
 * 2021/5/13
 */
public interface HttpPageRequestTask extends HttpRequestTask, HierarchicalTask {
	/**
	 * 获取当前的Http分页抓取URL链接处理
	 *
	 * @return 返回当前的分页处理逻辑
	 */
	PageStrategy getPageStrategy();

	/**
	 * 设置当前的父任务
	 *
	 * @param parent 当前的父任务
	 */
	void setParent(Task parent);
}
