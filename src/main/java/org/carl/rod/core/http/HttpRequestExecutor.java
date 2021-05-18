package org.carl.rod.core.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.carl.rod.core.http.handlers.HttpFinishedHandler;

import java.util.List;

/**
 * @author longjie
 * 2021/5/17
 */
public interface HttpRequestExecutor {

	/**
	 * 执行Http请求,并且解析其结果
	 *
	 * @param httpClient        指定的HttpClient 请求
	 * @param request           http请求包装对象
	 * @param failedHandlerList 请求失败执行处理器
	 * @return 返回对应的响应结果
	 */
	HttpResponse executeHttpRequest(CloseableHttpClient httpClient,
									HttpUriRequestWrapper request,
									List<HttpFinishedHandler> failedHandlerList);
}
