package org.carl.rod.config.http.url;

import java.util.List;

/**
 * 分组内容
 *
 * @author longjie
 * 2021/5/19
 */
public class DefaultUrlGroup implements UrlGroup {

	/**
	 * 当前组的编号
	 */
	private int groupSerialize;

	/**
	 * URL列表分组
	 */
	private List<String> urlGroup;

	/**
	 * 当前索引的位置
	 */
	private int index;

	public DefaultUrlGroup() {
	}

	public DefaultUrlGroup(int groupSerialize, List<String> urlGroup) {
		this.groupSerialize = groupSerialize;
		this.urlGroup = urlGroup;
	}

	public void setGroupSerialize(int groupSerialize) {
		this.groupSerialize = groupSerialize;
	}

	public void setUrlGroup(List<String> urlGroup) {
		this.urlGroup = urlGroup;
	}

	@Override
	public int getGroupCount() {
		return groupSerialize;
	}

	@Override
	public List<String> getGroupUrl() {
		return this.urlGroup;
	}

	@Override
	public boolean hasNext() {
		return index < urlGroup.size();
	}

	@Override
	public String next() {
		return urlGroup.get(index++);
	}
}
