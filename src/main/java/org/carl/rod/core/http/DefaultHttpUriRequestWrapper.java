package org.carl.rod.core.http;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author longjie
 * 2021/5/17
 */
public class DefaultHttpUriRequestWrapper implements HttpUriRequestWrapper {

	/**
	 * 原始的请求地址
	 */
	private String originUri;

	/**
	 * 目标请求对象
	 */
	private HttpUriRequest targetHttpUriRequest;

	public DefaultHttpUriRequestWrapper(String originUri, HttpUriRequest targetHttpUriRequest) {
		this.originUri = originUri;
		this.targetHttpUriRequest = targetHttpUriRequest;
	}

	@Override
	public String getOriginUri() {
		return originUri;
	}

	@Override
	public HttpUriRequest getHttpUriRequest() {
		return targetHttpUriRequest;
	}
}
