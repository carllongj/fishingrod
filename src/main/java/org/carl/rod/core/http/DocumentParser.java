package org.carl.rod.core.http;

import org.carl.rod.config.ctl.Document;

/**
 * @author longjie
 * 2021/5/18
 */
public interface DocumentParser {

	/**
	 * 提取对应文档中的内容,最终返回其对象
	 *
	 * @param doc 指定的文件内容
	 * @return 返回对应的对象
	 */
	Object parse(Document doc);
}
