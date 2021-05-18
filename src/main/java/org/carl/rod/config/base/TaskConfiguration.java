package org.carl.rod.config.base;

import org.carl.rod.config.task.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author longjie
 * 2021/5/13
 */
public class TaskConfiguration {

	/**
	 * 任务名称
	 */
	private String taskName;

	/**
	 * 进行分页查询任务设置
	 */
	private PageInfo pageConfig;

	/**
	 * http 请求url设置
	 */
	private String url;

	/**
	 * http请求方法设置
	 */
	private String httpMethod;

	/**
	 * 选择器映射
	 */
	private Map<String, List<String>> selector;

	/**
	 * 输出数据配置
	 */
	private OutputConfiguration output;

	/**
	 * http 请求参数和请求头设置
	 */
	private HttpRequestConfiguration httpConfig;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public PageInfo getPageConfig() {
		return pageConfig;
	}

	public void setPageConfig(PageInfo pageConfig) {
		this.pageConfig = pageConfig;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public HttpRequestConfiguration getHttpConfig() {
		return httpConfig;
	}

	public void setHttpConfig(HttpRequestConfiguration httpConfig) {
		this.httpConfig = httpConfig;
	}

	public Map<String, List<String>> getSelector() {
		return selector;
	}

	public void setSelector(Map<String, List<String>> selector) {
		this.selector = selector;
	}

	public OutputConfiguration getOutput() {
		return output;
	}

	public void setOutput(OutputConfiguration output) {
		this.output = output;
	}
}
