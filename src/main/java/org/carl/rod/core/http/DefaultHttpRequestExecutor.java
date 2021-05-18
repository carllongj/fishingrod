package org.carl.rod.core.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.carl.rod.core.http.handlers.HttpFinishedHandler;
import org.carl.rod.core.http.handlers.NoFinishHandlerFoundException;

import java.util.List;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/17
 */
public class DefaultHttpRequestExecutor implements HttpRequestExecutor {

	@Override
	public HttpResponse executeHttpRequest(CloseableHttpClient httpClient,
										   HttpUriRequestWrapper request,
										   List<HttpFinishedHandler> finishedHandlerList) {

		CloseableHttpResponse response = null;
		HttpResponse httpResponse;
		try {
			response = httpClient.execute(request.getHttpUriRequest());
		} catch (Exception e) {
			if (Objects.isNull(finishedHandlerList) || finishedHandlerList.isEmpty()) {
				throw new NoFinishHandlerFoundException("finished handler can not be empty");
			}

			for (HttpFinishedHandler handler : finishedHandlerList) {
				if (handler.isSupport(e)) {
					// 执行对应的失败逻辑
					HttpResponse targetResponse = handler.onFinish(httpClient, request, null, e);
					if (null != targetResponse) {
						// 当前获取到处理器
						response = targetResponse.getOriginHttpResponse();
					}
				}
			}

			if (response == null) {
				return null;
			}
		}

		/*
		 * 处理所有
		 */
		httpResponse = new DefaultHttpResponse(response, request);

		for (HttpFinishedHandler finishedHandler : finishedHandlerList) {
			if (finishedHandler.isSupport(response)) {
				HttpResponse finishResponse = finishedHandler.onFinish(httpClient, request,
					httpResponse, null);
				if (null != finishResponse) {
					return finishResponse;
				}
			}
		}
		// 返回默认的原始响应对象
		return httpResponse;
	}
}
