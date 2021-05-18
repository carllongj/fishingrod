package org.carl.rod.core.http;

import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * @author longjie
 * 2021/5/17
 */
public class DefaultHttpResponse extends AbstractCloseableHttpResponse {

	public DefaultHttpResponse(CloseableHttpResponse response, HttpUriRequestWrapper requestWrapper) {
		super(response, requestWrapper);
	}
}
