package org.carl.rod.core.http;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Http 请求出现异常处理器
 *
 * @author longjie
 * 2021/5/17
 */
public interface HttpFailedHandler {

	/**
	 * Http请求执行失败时处理器
	 *
	 * @param httpClient     Http请求执行客户端
	 * @param requestWrapper 封装的请求参数
	 * @param response       得到的Http响应结果
	 * @return 返回当前的处理器是否成功完成, 若后续不需要继续执行其它处理器
	 */
	boolean onFailed(CloseableHttpClient httpClient, HttpUriRequestWrapper requestWrapper, HttpResponse response);
}
