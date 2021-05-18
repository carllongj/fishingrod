package org.carl.rod.config.http;

/**
 * 默认Http响应的类型
 *
 * @author longjie
 * 2021/5/18
 */
public enum HttpContentType {

	/**
	 * 普通的文本,可能是一般的数据,可能为 <code>var = {} </code> 这类数据
	 */
	NORMAL,

	/**
	 * 标准格式的json数据
	 */
	JSON,

	/**
	 * 普通的HTML页面
	 */
	HTML;

	/**
	 * 查找支持的Content类型
	 *
	 * @param contentType 对应的响应内容
	 * @return 返回对应的 HttpContentType
	 */
	public static HttpContentType findSupportContentType(String contentType) {
		switch (contentType) {
			case HttpKeys.APPLICATION_JSON:
				return JSON;
			case HttpKeys.TEXT_HTML:
				return HTML;
		}

		throw new UnsupportedContentTypeException("unsupported Content Type %s", contentType);
	}
}
