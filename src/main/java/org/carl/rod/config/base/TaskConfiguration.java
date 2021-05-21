package org.carl.rod.config.base;

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
	 * 获取URL配置
	 */
	private UrlProviderConfiguration urlsProvider;

	/**
	 * http请求方法设置
	 */
	private String httpMethod;

	/**
	 * 选择器映射
	 */
	private Map<String, List<String>> selectors;

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

	public UrlProviderConfiguration getUrlsProvider() {
		return urlsProvider;
	}

	public void setUrlsProvider(UrlProviderConfiguration urlsProvider) {
		this.urlsProvider = urlsProvider;
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

	public Map<String, List<String>> getSelectors() {
		return selectors;
	}

	public void setSelectors(Map<String, List<String>> selectors) {
		this.selectors = selectors;
	}

	public OutputConfiguration getOutput() {
		return output;
	}

	public void setOutput(OutputConfiguration output) {
		this.output = output;
	}
}
