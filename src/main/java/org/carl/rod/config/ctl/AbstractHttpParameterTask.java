package org.carl.rod.config.ctl;

import org.apache.http.impl.client.CloseableHttpClient;
import org.carl.rod.config.task.HttpRequestTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author longjie
 * 2021/5/13
 */
public abstract class AbstractHttpParameterTask extends AbstractCtlTask implements HttpRequestTask {

	/**
	 * 当前任务的请求方法
	 */
	private String httpMethod;

	/**
	 * 当前任务的请求方法头
	 */
	private volatile Map<String, List<String>> requestHeaders;

	/**
	 * 当前任务的请求参数
	 */
	private volatile Map<String, List<String>> requestParameters;

	/**
	 * 访问链接地址的根路径
	 */
	private String baseUrl;

	/**
	 * http请求客户端
	 */
	private CloseableHttpClient httpClient;

	public AbstractHttpParameterTask() {
	}

	public AbstractHttpParameterTask(String taskName) {
		super(taskName);
	}

	@Override
	public Map<String, List<String>> getRequestHeaders() {
		return this.requestHeaders;
	}

	@Override
	public Map<String, List<String>> getRequestParameters() {
		return this.requestParameters;
	}

	@Override
	public void addRequestHeader(String headerKey, String headValue) {
		List<String> headerValues = this.requestHeaders.get(headerKey);
		if (null == headerValues) {
			headerValues = new ArrayList<>();
		}
		headerValues.add(headValue);
	}

	@Override
	public void addRequestParameter(String key, String value) {
		List<String> requestParameters = this.requestParameters.get(key);
		if (null == requestParameters) {
			requestParameters = new ArrayList<>();
		}
		requestParameters.add(value);
	}

	@Override
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	@Override
	public String getHttpMethod() {
		return this.httpMethod;
	}

	@Override
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public String getBaseUrl() {
		return baseUrl;
	}

	@Override
	public void setHttpComponent(CloseableHttpClient httpComponent) {
		this.httpClient = httpComponent;
	}
}
