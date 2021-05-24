package org.carl.rod.config.task;

import org.apache.http.impl.client.CloseableHttpClient;
import org.carl.rod.config.http.url.ConfigurableGroupedUrlProvider;
import org.carl.rod.config.http.url.GroupedUrlProvider;
import org.carl.rod.core.http.HttpRequestExecutor;

import java.util.List;
import java.util.Map;

/**
 * @author longjie
 * 2021/5/13
 */
public interface HttpRequestTask extends HierarchicalTask, ConfigurableGroupedUrlProvider {

	/**
	 * 获取当前任务参数的请求头
	 *
	 * @return 返回任务参数请求头
	 */
	Map<String, List<String>> getRequestHeaders();

	/**
	 * 新增请求头参数
	 *
	 * @param headerKey 请求头key
	 * @param headValue 请求头value
	 */
	void addRequestHeader(String headerKey, String headValue);

	/**
	 * 获取当前的请求参数
	 *
	 * @return 返回当前的请求参数
	 */
	Map<String, List<String>> getRequestParameters();

	/**
	 * 新增请求参数
	 *
	 * @param key   指定的请求key
	 * @param value 请求value
	 */
	void addRequestParameter(String key, String value);

	/**
	 * 设置当前请求方式
	 *
	 * @param httpMethod 指定的请求方式
	 */
	void setHttpMethod(String httpMethod);

	/**
	 * 获取当前的请求方式
	 *
	 * @return 返回当前请求方式
	 */
	String getHttpMethod();

	/**
	 * 指定当前任务的链接地址
	 *
	 * @param urlProvider URL地址请求分组
	 */
	void setUrlProvider(GroupedUrlProvider urlProvider);

	/**
	 * 获取当前设置的根路径
	 *
	 * @return 获取当前路径地址
	 */
	String getCurrentUrl();

	/**
	 * 设置当前的http客户端
	 *
	 * @param httpComponent 指定的http请求客户端
	 */
	void setHttpComponent(CloseableHttpClient httpComponent);

	/**
	 * 获取当前的HttpClient 对象
	 *
	 * @return 返回当前设置的httpClient
	 */
	CloseableHttpClient getHttpClient();

	/**
	 * Http 请求执行器
	 *
	 * @param executor http请求执行器
	 */
	void setHttpExecutor(HttpRequestExecutor executor);

	/**
	 * 获取当前的Http请求执行器
	 *
	 * @return 返回对应的请求执行器
	 */
	HttpRequestExecutor getHttpExecutor();

	/**
	 * 设置当前任务的父任务
	 *
	 * @param parent 父任务
	 */
	void setParent(Task parent);
}
