package org.carl.rod.config.http;

import org.carl.rod.core.http.DocumentParser;

/**
 * @author longjie
 * 2021/5/27
 */
public class PageUrlHttpMappedValueTaskInputHandler extends HttpMappedValueTaskInputHandler {

	@Override
	protected DocumentParser findHttpDocumentParser(HttpContentType httpContentType) {
		if (httpContentType == HttpContentType.HTML) {
			return new PageUrlHtmlMappedValueDocumentParser(selectors);
		}
		throw new UnsupportedContentTypeException("HttpContentType %s is not supported", httpContentType);
	}
}
