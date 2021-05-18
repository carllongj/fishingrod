package org.carl.rod.core.http.handlers;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.carl.rod.core.http.HttpResponse;
import org.carl.rod.core.http.HttpUriRequestWrapper;

/**
 * @author longjie
 * 2021/5/18
 */
public class RetryHttpFinishedHandler implements HttpFinishedHandler {

	private static final Integer DEFAULT_RETRY_TIMES = 3;

	private int retryTimes;

	public RetryHttpFinishedHandler() {
		this.retryTimes = DEFAULT_RETRY_TIMES;
	}

	public RetryHttpFinishedHandler(int retryTimes) {
		this.retryTimes = retryTimes;
	}

	@Override
	public boolean isSupport(Object value) {
		return value instanceof ConnectTimeoutException;
	}

	/**
	 * @param httpClient     Http请求执行客户端
	 * @param requestWrapper 封装的请求参数
	 * @param response       得到的Http响应结果
	 * @param throwable      上层得到的异常
	 * @return 返回重试的Http响应结果
	 */
	@Override
	public HttpResponse onFinish(CloseableHttpClient httpClient, HttpUriRequestWrapper requestWrapper, HttpResponse response, Throwable throwable) {

		/*
		   当前为异常处理器,response响应为null
		 */
		return null;
	}
}
