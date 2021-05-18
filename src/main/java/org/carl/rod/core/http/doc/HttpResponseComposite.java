package org.carl.rod.core.http.doc;

import org.carl.rod.core.http.HttpResponse;
import org.carl.rod.core.http.HttpUriRequestWrapper;

/**
 * @author longjie
 * 2021/5/18
 */
public final class HttpResponseComposite {

	private HttpResponse response;

	private HttpUriRequestWrapper httpUriRequestWrapper;

	public HttpResponseComposite(HttpUriRequestWrapper httpUriRequestWrapper, HttpResponse response) {
		this.httpUriRequestWrapper = httpUriRequestWrapper;
		this.response = response;
	}

	public HttpResponse getResponse() {
		return response;
	}

	public HttpUriRequestWrapper getHttpUriRequestWrapper() {
		return httpUriRequestWrapper;
	}
}
