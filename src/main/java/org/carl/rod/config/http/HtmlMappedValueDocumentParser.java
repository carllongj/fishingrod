package org.carl.rod.config.http;

import org.carl.rod.config.ctl.Document;
import org.carl.rod.core.http.DocumentParser;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longjie
 * 2021/5/18
 */
class HtmlMappedValueDocumentParser implements DocumentParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlMappedValueDocumentParser.class);

	/**
	 * 记录当前选择器的映射,KEY表示最终生成的map字段的KEY值,而value则表示多个css选择器用以选择对应的
	 */
	private Map<String, List<String>> selectorMap;

	public HtmlMappedValueDocumentParser(Map<String, List<String>> selectorMap) {
		this.selectorMap = selectorMap;
	}

	@Override
	public Object parse(Document doc) {
		HttpDocument realDocument = (HttpDocument) doc;
		org.jsoup.nodes.Document document = Jsoup.parse(realDocument.getResponseContent());
		if (null == selectorMap || selectorMap.isEmpty()) {
			LOGGER.warn("doc {} no selector specific ignore current link", realDocument.getOriginUrl());
		}

		// 结果采集的
		Map<String, String> extractMap = new LinkedHashMap<>();

		nextValue:
		for (Map.Entry<String, List<String>> entry : selectorMap.entrySet()) {
			for (String selection : entry.getValue()) {
				Elements elements = document.select(selection);
				if (elements.isEmpty()) {
					continue;
				}

				if (elements.size() == 1) {
					extractMap.put(entry.getKey(), elements.get(0).text());
					continue nextValue;
				}
			}
		}
		return new HttpMappedValue(extractMap);
	}
}
