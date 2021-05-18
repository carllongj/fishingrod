package org.carl.rod.core.http.handlers;

import org.apache.http.impl.client.CloseableHttpClient;
import org.carl.rod.core.http.HttpResponse;
import org.carl.rod.core.http.HttpUriRequestWrapper;

/**
 * Http 请求出现异常处理器
 *
 * @author longjie
 * 2021/5/17
 */
public interface HttpFinishedHandler {

	/**
	 * 当前处理器是否支持处理该情况
	 *
	 * @param value 目标对象
	 * @return 返回是否支持处理
	 */
	boolean isSupport(Object value);

	/**
	 * Http请求执行失败时处理器
	 *
	 * @param httpClient     Http请求执行客户端
	 * @param requestWrapper 封装的请求参数
	 * @param response       得到的Http响应结果
	 * @param throwable      上层得到的异常
	 * @return 返回对应的HttpResponse
	 */
	HttpResponse onFinish(CloseableHttpClient httpClient,
						  HttpUriRequestWrapper requestWrapper,
						  HttpResponse response,
						  Throwable throwable);
}
