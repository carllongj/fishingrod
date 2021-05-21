package org.carl.rod.config.base;

import org.carl.rod.config.task.PageInfo;

/**
 * @author longjie
 * 2021/5/21
 */
public class HttpUrlConfiguration {

	/**
	 * 设置分页的信息
	 */
	private PageInfo pageConfig;

	/**
	 * 设置当前的任务名称
	 */
	private String taskName;

	/**
	 * 设置请求参数
	 */
	private String httpMethod;

	public PageInfo getPageConfig() {
		return pageConfig;
	}

	public void setPageConfig(PageInfo pageConfig) {
		this.pageConfig = pageConfig;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
}
