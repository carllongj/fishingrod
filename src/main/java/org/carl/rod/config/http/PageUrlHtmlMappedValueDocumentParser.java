package org.carl.rod.config.http;

import org.carl.rod.utils.PathUtils;
import org.carl.rod.utils.StringUtils;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/27
 */
class PageUrlHtmlMappedValueDocumentParser extends HtmlMappedValueDocumentParser {

	/**
	 * 链接对应的属性
	 */
	private static final String HREF_VALUE = "href";

	public PageUrlHtmlMappedValueDocumentParser(Map<String, List<String>> selectorMap) {
		super(selectorMap);
	}

	@Override
	protected String extractValue(Element element, HttpDocument document) {
		// 得到链接地址
		String href = element.attr(HREF_VALUE);
		if (Objects.isNull(href) || href.isEmpty()) {
			return href;
		}
		// 若以HTTP开头,则直接返回对应的链接地址
		if (href.startsWith(HttpDocument.HTTP_PROTOCOL_NAME)) {
			return href;
		}

		if (href.startsWith(HttpDocument.ABSOLUTE_PATH)) {
			return PathUtils.get(StringUtils.extractHttpBaseUrl(document.getOriginUrl()), href);
		}

		return PathUtils.get(document.getOriginUrl(), href);
	}
}
