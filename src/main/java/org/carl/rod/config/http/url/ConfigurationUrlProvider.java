package org.carl.rod.config.http.url;

import java.util.Collections;
import java.util.List;

/**
 * 通过配置文件配置的url
 *
 * @author longjie
 * 2021/5/19
 */
public class ConfigurationUrlProvider extends AbstractGroupedUrlProvider {

	/**
	 * 需要处理的url列表
	 */
	private List<String> urlList;

	/**
	 * 当前记录编号
	 */
	private int currentRecord;

	public ConfigurationUrlProvider() {
	}

	public ConfigurationUrlProvider(List<String> urlList) {
		this.urlList = urlList;
	}

	/**
	 * 存储从配置文件中获取的链接列表
	 *
	 * @param urlList
	 */
	public void setUrlList(List<String> urlList) {
		this.urlList = Collections.unmodifiableList(urlList);
	}

	@Override
	protected List<String> urlsGroup() {
		int endIndex = Math.min((currentRecord + getGroupSize()), urlList.size());
		List<String> subList = urlList.subList(currentRecord, endIndex);

		//更新索引位置
		currentRecord = endIndex;
		return subList;
	}

	@Override
	public boolean hasNext() {
		return this.currentRecord < urlList.size();
	}
}
