package org.carl.rod.config.page;

import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.AbstractHttpParameterTask;
import org.carl.rod.config.task.PageInfo;

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
}
