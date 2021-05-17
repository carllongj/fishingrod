package org.carl.rod.config.page;

import org.carl.rod.config.task.PageInfo;

import java.util.Objects;

/**
 * @author longjie
 * 2021/5/10
 */
public class PageFormatStrategy implements PageStrategy {

	private PageInfo pageInfo;

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public PageFormatStrategy() {
	}

	public PageFormatStrategy(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Override
	public String getPageUrl(Integer page) {
		if (Objects.isNull(pageInfo)) {
			return null;
		}
		// 若传入为null,0,1 ,都将被认为是第一页
		if (Objects.isNull(page) || page == 0 || page == 1) {
			return pageInfo.getPageUrl() + pageInfo.getFirstPage();
		}
		// 其它的将根据对应的请求进行处理
		return pageInfo.getPageUrl() + String.format(pageInfo.getPageFormat(), page);
	}

	@Override
	public int getTotalPage() {
		if (Objects.isNull(pageInfo)) {
			return 0;
		}
		return pageInfo.getPageCount() == null ? 0 : pageInfo.getPageCount();
	}
}
