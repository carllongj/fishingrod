package org.carl.rod.config.page;

import org.carl.rod.config.RodException;
import org.carl.rod.config.ctl.AbstractHttpParameterTask;
import org.carl.rod.config.task.PageInfo;
import org.carl.rod.core.http.DefaultHttpUriRequestWrapper;
import org.carl.rod.core.http.HttpResponse;
import org.carl.rod.core.http.HttpUriRequestWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longjie
 * 2021/5/13
 */
public class DefaultPageRequestTask extends AbstractHttpParameterTask implements HttpPageRequestTask {

	/**
	 * 分页查询请求参数信息
	 */
	private PageInfo pageInfo;

	/**
	 * 默认的分页策略
	 */
	private PageStrategy pageStrategy;

	/**
	 * 默认构造器
	 */
	public DefaultPageRequestTask() {
	}

	public DefaultPageRequestTask(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public DefaultPageRequestTask(String taskName, PageInfo pageInfo) {
		super(taskName);
		this.pageInfo = pageInfo;
	}

	@Override
	public PageStrategy getPageStrategy() {
		if (null != pageInfo) {
			this.pageStrategy = new PageFormatStrategy(pageInfo);
		}
		return pageStrategy;
	}

	@Override
	protected List<HttpUriRequestWrapper> createTaskRequestList() {

		if (null == pageInfo) {
			// not happened
			throw new RodException("none pageConfig found");
		}
		List<HttpUriRequestWrapper> httpUriRequests = new ArrayList<>();

		PageStrategy pageStrategy = this.getPageStrategy();
		for (int i = 1; i <= pageStrategy.getTotalPage(); i++) {
			String uri = pageStrategy.getPageUrl(i);
			// 收集所有的http请求数据
			httpUriRequests.add(new DefaultHttpUriRequestWrapper(uri, this.buildHttpRequest(uri)));
		}

		return httpUriRequests;
	}

	@Override
	protected String getProvidedUri() {
		return this.pageInfo.getPageUrl();
	}
}
