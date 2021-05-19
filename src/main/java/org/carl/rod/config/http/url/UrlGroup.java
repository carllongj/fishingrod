package org.carl.rod.config.http.url;

import java.util.List;

/**
 * @author longjie
 * 2021/5/19
 */
public interface UrlGroup {

	/**
	 * 获取当前的分组编号
	 *
	 * @return 返回对应的分组编号信息
	 */
	int getGroupCount();

	/**
	 * 获取得到对应对组的URL信息
	 *
	 * @return 返回对应的URL分组信息
	 */
	List<String> getGroupUrl();
}
