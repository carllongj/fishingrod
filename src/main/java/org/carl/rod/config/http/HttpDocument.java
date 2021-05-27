package org.carl.rod.config.http;

import org.carl.rod.config.ctl.Document;

import java.nio.charset.Charset;

/**
 * @author longjie
 * 2021/5/18
 */
public interface HttpDocument extends Document {

	String HTTP_PROTOCOL_NAME = "http";

	/**
	 * 绝对路径开头
	 */
	String ABSOLUTE_PATH = "/";

	/**
	 * 获取原始
	 *
	 * @return 返回此文档对应对应的http请求链接
	 */
	String getOriginUrl();

	/**
	 * 获取当前文档类型
	 *
	 * @return 返回对应的HttpContentType
	 */
	HttpContentType getHttpContentType();

	/**
	 * 获取当前文档对应的编码
	 *
	 * @return 返回文档对应的编码
	 */
	Charset getCharset();

	/**
	 * 获取对应的响应文本内容
	 *
	 * @return 返回响应的内容
	 */
	String getResponseContent();
}
