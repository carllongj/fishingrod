package org.carl.rod.core.task;

import java.util.List;

/**
 * @author longjie
 * 2021/5/21
 */
public interface PageConfig {

	/**
	 * 获取总的分页数量
	 *
	 * @return 返回总的分页数量
	 */
	int getTotalPages();

	/**
	 * 获取当前分页数据配置的格式
	 *
	 * @return 返回对应的模板
	 */
	String getPageFormat();

	/**
	 * 获取当前分页的基础路径
	 *
	 * @return 返回对应的基础路径
	 */
	String getPageUrl();

	/**
	 * 获取当前的url选择器
	 *
	 * @return 返回所有的url 链接选择器
	 */
	List<String> getUrlSelector();
}
