package org.carl.rod.core.http;

import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.carl.rod.config.http.HttpKeys;
import org.carl.rod.utils.HttpComponentsUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author longjie
 * 2021/5/17
 */
public abstract class AbstractCloseableHttpResponse implements HttpResponse, LinkWrapper {

	protected final CloseableHttpResponse response;

	/**
	 * 当前的文本内容
	 */
	private String content;

	/**
	 * 所有的响应头信息
	 */
	private Map<String, String> responseHeaders;

	/**
	 * 请求包装对象
	 */
	private HttpUriRequestWrapper requestWrapper;

	protected AbstractCloseableHttpResponse(CloseableHttpResponse response, HttpUriRequestWrapper requestWrapper) {
		this.response = response;
		this.requestWrapper = requestWrapper;
		this.parseResponse(response);
	}

	@Override
	public HttpUriRequestWrapper getOriginRequest() {
		return this.requestWrapper;
	}

	@Override
	public CloseableHttpResponse getOriginHttpResponse() {
		return this.response;
	}

	/**
	 * 响应码
	 */
	private int code;

	/**
	 * 响应的类型
	 */
	private ContentType contentType;

	/**
	 * 解析外部的请求
	 *
	 * @param response 响应内容
	 */
	private void parseResponse(CloseableHttpResponse response) {
		try {
			StatusLine statusLine = this.response.getStatusLine();
			// 获取状态码
			this.code = statusLine.getStatusCode();
			// 获取响应正文
			this.content = EntityUtils.toString(response.getEntity());
			// 获取所有的请求头
			Header[] headers = response.getAllHeaders();
			if (null != headers && headers.length != 0) {
				Map<String, String> headersResult = new LinkedHashMap<>();
				for (Header header : headers) {
					headersResult.put(header.getName(), header.getValue());
				}
				this.responseHeaders = headersResult;
			} else {
				this.responseHeaders = Collections.emptyMap();
			}
			this.contentType = ContentType.parse(responseHeaders.get(HttpKeys.CONTENT_TYPE));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HttpComponentsUtils.closeHttpResourceQuietly(response);
		}
	}

	@Override
	public String getResponseContent() {
		return this.content;
	}

	@Override
	public int getResponseCode() {
		return code;
	}

	@Override
	public String getHeaderValue(String headerKey) {
		return this.responseHeaders.getOrDefault(headerKey, null);
	}

	@Override
	public boolean isHyperTextDocument() {
		return this.contentType == ContentType.TEXT_HTML;
	}

	@Override
	public void close() throws IOException {
		if (null != response) {
			response.close();
		}
	}

	@Override
	public ContentType getContentType() {
		return contentType;
	}
}
