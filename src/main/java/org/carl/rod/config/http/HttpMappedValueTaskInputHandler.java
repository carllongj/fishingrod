package org.carl.rod.config.http;

import org.carl.rod.config.ctl.Document;
import org.carl.rod.config.ctl.TaskInputHandler;
import org.carl.rod.core.http.DocumentParser;

import java.util.List;
import java.util.Map;

/**
 * 通过外部映射指定的key-value数据来执行处理
 *
 * @author longjie
 * 2021/5/18
 */
public class HttpMappedValueTaskInputHandler implements TaskInputHandler {

	/**
	 * 对于Html页面的选择器
	 */
	protected Map<String, List<String>> selectors;

	public void setSelectors(Map<String, List<String>> selectors) {
		this.selectors = selectors;
	}

	@Override
	public Object handle(Document taskInput) {
		HttpDocument document = (HttpDocument) taskInput;
		HttpContentType contentType = document.getHttpContentType();
		return this.findHttpDocumentParser(contentType).parse(document);
	}

	/**
	 * 查找一个默认的对应的文档解析器
	 *
	 * @param httpContentType 指定的文档类型
	 * @return 返回文档解析器
	 */
	protected DocumentParser findHttpDocumentParser(HttpContentType httpContentType) {
		if (httpContentType == HttpContentType.HTML) {
			return new HtmlMappedValueDocumentParser(selectors);
		}
		throw new UnsupportedContentTypeException("HttpContentType %s is not supported", httpContentType);
	}
}
