package org.carl.rod.config.task;

import java.util.Objects;

/**
 * @author longjie
 * 2021/5/17
 */
public enum HttpMethod {

	/**
	 * GET 请求
	 */
	HTTP_GET("GET", "org.apache.http.client.methods.HttpGet"),

	/**
	 * POST 请求
	 */
	HTTP_POST("POST", "org.apache.http.client.methods.HttpPost"),

	/**
	 * Options 请求 请求
	 */
	HTTP_OPTIONS("OPTIONS", "org.apache.http.client.methods.HttpOptions"),
	/**
	 * DELETE 请求
	 */
	HTTP_DELETE("DELETE", "org.apache.http.client.methods.HttpDelete"),
	/**
	 * PUT 请求
	 */
	HTTP_PUT("PUT", "org.apache.http.client.methods.HttpPut"),
	/**
	 * TRACE 请求
	 */
	HTTP_TRACE("TRACE", "org.apache.http.client.methods.HttpTrace"),
	/**
	 * PATCH 请求
	 */
	HTTP_PATCH("PATCH", "org.apache.http.client.methods.HttpPatch"),
	/**
	 * HEAD 请求
	 */
	HTTP_HEAD("HEAD", "org.apache.http.client.methods.HttpHead");
	/**
	 * Http 请求方法名称
	 */
	private String methodName;

	/**
	 * Http 请求方法类名称
	 */
	private String httpMethodClass;

	/**
	 * @param methodName      请求方法名称
	 * @param httpMethodClass 请求方法类名称
	 */
	HttpMethod(String methodName, String httpMethodClass) {
		this.methodName = methodName;
		this.httpMethodClass = httpMethodClass;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getHttpMethodClass() {
		return httpMethodClass;
	}

	/**
	 * 获取一个Http请求方式
	 *
	 * @param methodName 请求方法名称
	 * @return 返回对应的请求方式
	 */
	public static HttpMethod findHttpMethod(String methodName) {
		for (HttpMethod method : values()) {
			if (method.getMethodName().equalsIgnoreCase(methodName)) {
				return method;
			}
		}
		return null;
	}

	/**
	 * 获取一个方法,或者返回默认的方法
	 *
	 * @param methodName 指定的方法名称
	 * @return 返回默认方法
	 */
	public static HttpMethod getMethodOrDefault(String methodName) {
		HttpMethod httpMethod = findHttpMethod(methodName);
		return Objects.isNull(httpMethod) ? HTTP_GET : httpMethod;
	}
}
