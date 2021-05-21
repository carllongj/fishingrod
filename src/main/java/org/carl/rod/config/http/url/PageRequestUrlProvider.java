package org.carl.rod.config.http.url;

import org.carl.rod.config.page.PageStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author longjie
 * 2021/5/21
 */
public class PageRequestUrlProvider extends AbstractGroupedUrlProvider {

	/**
	 * 默认起始页
	 */
	private static final int DEFAULT_START_PAGE = 1;

	/**
	 * 分页策略
	 */
	private PageStrategy pageStrategy;

	/**
	 * 当前页信息
	 */
	private int currentPage;

	public PageRequestUrlProvider() {
		this.currentPage = DEFAULT_START_PAGE;
	}

	public void setPageStrategy(PageStrategy pageStrategy) {
		this.pageStrategy = pageStrategy;
	}

	@Override
	protected List<String> urlsGroup() {
		List<String> urlList = new ArrayList<>();
		int i = 0, groupSize = this.getGroupSize();
		// 当前分组一共要读取指定groupSize 的url链接个数
		while (i++ < groupSize) {
			if (currentPage > pageStrategy.getTotalPage()) {
				break;
			}
			urlList.add(pageStrategy.getPageUrl(currentPage++));
		}
		return Collections.unmodifiableList(urlList);
	}

	@Override
	public boolean hasNext() {
		return this.currentPage <= pageStrategy.getTotalPage();
	}
}
