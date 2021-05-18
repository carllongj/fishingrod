package org.carl.rod.core.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;

import java.io.Closeable;

/**
 * @author longjie
 * 2021/5/17
 */
public interface HttpResponse extends Closeable {

	/**
	 * 获取原始的Http请求对象
	 *
	 * @return 返回对应的HttpResponse
	 */
	CloseableHttpResponse getOriginHttpResponse();

	/**
	 * 获取响应的html页面请求,若为页面则返回页面的内容,若为非页面数据,则返回对应的内容
	 *
	 * @return 返回当前的响应正文, 若当前是一个页面, 则返回对应的html页面内容
	 */
	String getResponseContent();

	/**
	 * 获取当前执行完成后的响应码值
	 *
	 * @return 返回响应码
	 */
	int getResponseCode();

	/**
	 * 获取指定请求头的值集合
	 *
	 * @param headerKey 指定的headerKey
	 * @return 返回对应的值集合
	 */
	String getHeaderValue(String headerKey);

	/**
	 * 判断当前的响应是否为超文本文档
	 *
	 * @return 返回是否为html页面
	 */
	boolean isHyperTextDocument();

	/**
	 * 获取原始的响应任务类型
	 *
	 * @return 返回对应的响应类型
	 */
	ContentType getContentType();
}
