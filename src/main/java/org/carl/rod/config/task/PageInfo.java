package org.carl.rod.config.task;

import org.carl.rod.core.task.PageConfig;

import java.util.List;

/**
 * @author longjie
 * 2021/5/10
 */
public class PageInfo implements PageConfig {

	/**
	 * 首页的链接地址
	 */
	private String firstPage;

	/**
	 * 链接地址的格式
	 */
	private String pageFormat;

	/**
	 * 分页数据请求路径地址
	 * 地址的完整路径由 pageUrl + firstPage -> 第一页路径地址
	 * 其它页的完整路径地址由 pageUrl + pageFormat -> 其它页路径地址
	 */
	private String pageUrl;

	/**
	 * 当前的分页记录总数
	 */
	private Integer pageCount;

	/**
	 * 链接获取的 css 选择器
	 */
	private List<String> urlSelector;

	@Override
	public int getTotalPages() {
		return 0;
	}

	public String getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	@Override
	public String getPageFormat() {
		return pageFormat;
	}

	public void setPageFormat(String pageFormat) {
		this.pageFormat = pageFormat;
	}

	@Override
	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public List<String> getUrlSelector() {
		return urlSelector;
	}

	public void setUrlSelector(List<String> urlSelector) {
		this.urlSelector = urlSelector;
	}
}
