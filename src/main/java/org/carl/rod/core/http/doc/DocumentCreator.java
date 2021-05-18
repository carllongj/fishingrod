package org.carl.rod.core.http.doc;

import org.carl.rod.config.ctl.Document;

/**
 * @author longjie
 * 2021/5/18
 */
public interface DocumentCreator {

	/**
	 * 是否支持该种类型对象创建
	 *
	 * @param value 指定的对象
	 * @return 返回是否支持
	 */
	boolean isSupport(Object value);

	/**
	 * 进行创建对应的文档
	 *
	 * @param value 指定的对象
	 * @return 返回对应的文档类型
	 */
	Document createDocument(Object value);
}
