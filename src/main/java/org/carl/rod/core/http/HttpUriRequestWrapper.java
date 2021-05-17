package org.carl.rod.core.http;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author longjie
 * 2021/5/17
 */
public interface HttpUriRequestWrapper {

	/**
	 * 获取当前原始配置的请求数据
	 *
	 * @return
	 */
	String getOriginUri();

	/**
	 * 获取当前已经构建好的RequestBuilder
	 *
	 * @return 返回对应的HttpUriRequest
	 */
	HttpUriRequest getHttpUriRequest();
}
