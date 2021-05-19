package org.carl.rod.config.http.url;

import java.util.Iterator;

/**
 * Url获取的根接口,通过该接口获取所有的url链接信息
 * // TODO: 2021/5/19 由于链接可能存在许多,并且一次性可能执行不完成,为了提高内存利用率,后续会将此接口扩展
 * // TODO: 2021/5/19 将获取到的数据进行分组返回
 *
 * @author longjie
 * 2021/5/19
 */
public interface GroupedUrlProvider extends Iterator<UrlGroup> {

	/**
	 * 设置分组大小
	 *
	 * @param groupSize 指定的分组大小
	 */
	void setGroupSize(int groupSize);

	/**
	 * 获取当前分组的大小
	 *
	 * @return 返回当前分组的大小
	 */
	int getGroupSize();

}
