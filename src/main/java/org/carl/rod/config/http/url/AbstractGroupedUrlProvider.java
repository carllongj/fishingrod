package org.carl.rod.config.http.url;

import java.util.Collections;
import java.util.List;

/**
 * @author longjie
 * 2021/5/19
 */
public abstract class AbstractGroupedUrlProvider implements GroupedUrlProvider {

	/**
	 * 记录分组编号
	 */
	private int groupSerializeGenerator;

	/**
	 * 分组的大小
	 */
	private int groupSize;

	/**
	 * 默认分组大小
	 */
	private static final int DEFAULT_GROUP_SIZE = 200;

	public AbstractGroupedUrlProvider() {
		this.groupSize = DEFAULT_GROUP_SIZE;
	}

	@Override
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	@Override
	public int getGroupSize() {
		return groupSize;
	}

	@Override
	public final UrlGroup next() {
		return new DefaultUrlGroup(groupSerializeGenerator++, Collections.unmodifiableList(urlsGroup()));
	}

	/**
	 * 获取到一个分组的url列表
	 *
	 * @return 返回当前分组的内容
	 */
	protected abstract List<String> urlsGroup();
}
