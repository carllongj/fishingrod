package org.carl.rod.core.http;

/**
 * @author longjie
 * 2021/5/18
 */
public interface LinkWrapper {

	/**
	 * 获取原始的请求链接地址
	 *
	 * @return 返回对应的原始请求
	 */
	HttpUriRequestWrapper getOriginRequest();
}
