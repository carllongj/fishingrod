package org.carl.rod.core.http;

import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

/**
 * @author longjie
 * 2021/5/17
 */
public class DefaultHttpRequestExecutor implements HttpRequestExecutor {

	@Override
	public HttpResponse executeHttpRequest(CloseableHttpClient httpClient, HttpUriRequestWrapper request, List<HttpFailedHandler> failedHandlerList) {
		return null;
	}
}
