package org.carl.rod.core.task.definition;

import org.carl.rod.core.task.PageConfig;

/**
 * @author longjie
 * 2021/5/21
 */
public interface HttpTaskDefinition {

	/**
	 * 获取当前任务的名称
	 *
	 * @return 返回当前的任务名称
	 */
	String getTaskName();

	/**
	 * 获取当前的任务http
	 *
	 * @return 返回当前请求执行的方法
	 */
	String getHttpMethod();

	/**
	 * 获取当前分页HttpRequest 参数
	 *
	 * @return 返回当前的分页配置信息, 若当前没有则返回 <code>null</code>
	 */
	PageConfig getPageConfig();
}
