package org.carl.rod.config.http;

import org.carl.rod.core.http.HttpResponse;
import org.carl.rod.core.http.HttpUriRequestWrapper;
import org.carl.rod.core.http.doc.HttpResponseComposite;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author longjie
 * 2021/5/18
 */
public class DefaultHttpDocument implements HttpDocument {

	private HttpResponse httpResponse;

	private HttpUriRequestWrapper httpUriRequestWrapper;

	public DefaultHttpDocument(HttpResponseComposite composite) {
		this.httpResponse = composite.getResponse();
		this.httpUriRequestWrapper = composite.getHttpUriRequestWrapper();
	}

	public DefaultHttpDocument(HttpResponse response, HttpUriRequestWrapper requestWrapper) {
		this.httpResponse = response;
		this.httpUriRequestWrapper = requestWrapper;
	}

	@Override
	public String getOriginUrl() {
		return httpUriRequestWrapper.getOriginUri();
	}

	@Override
	public HttpContentType getHttpContentType() {
		return HttpContentType.findSupportContentType(httpResponse.getContentType().getMimeType());
	}

	@Override
	public Charset getCharset() {
		Charset charset = httpResponse.getContentType().getCharset();
		if (charset == null) {
			charset = StandardCharsets.UTF_8;
		}
		return charset;
	}

	@Override
	public String getResponseContent() {
		return httpResponse.getResponseContent();
	}

	@Override
	public byte[] getDocumentContent() {
		return httpResponse.getResponseContent().getBytes(getCharset());
	}
}
