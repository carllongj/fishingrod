package org.carl.rod.config.page;

import org.carl.rod.config.RodException;
import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.AbstractHttpParameterTask;
import org.carl.rod.config.http.url.UrlGroup;
import org.carl.rod.config.task.PageInfo;
import org.carl.rod.core.http.DefaultHttpUriRequestWrapper;
import org.carl.rod.core.http.HttpUriRequestWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longjie
 * 2021/5/13
 */
@Deprecated
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

	public DefaultPageRequestTask(TaskConfiguration taskConfiguration, PageInfo pageInfo) {
		super(taskConfiguration);
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
	protected List<HttpUriRequestWrapper> createTaskRequestList(UrlGroup urlGroup) {

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
}
