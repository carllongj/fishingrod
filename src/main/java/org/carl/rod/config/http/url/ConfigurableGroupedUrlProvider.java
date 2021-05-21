package org.carl.rod.config.http.url;

/**
 * @author longjie
 * 2021/5/21
 */
public interface ConfigurableGroupedUrlProvider {

	/**
	 * 尾部新增一个UrlProvider
	 *
	 * @param groupedUrlProvider 新增的URLProvider
	 */
	void addLast(GroupedUrlProvider groupedUrlProvider);

	/**
	 * 头部新增一个 groupedUrlProvider
	 *
	 * @param groupedUrlProvider 新增的URLProvider
	 */
	void addFirst(GroupedUrlProvider groupedUrlProvider);
}
