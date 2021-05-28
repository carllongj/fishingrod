/*
 * Copyright 2021 carllongj
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package org.carl.rod.config.http.url;

import java.util.LinkedList;
import java.util.List;

/**
 * @author carllongj
 * 2021/5/20 13:23
 */
public class CompositeGroupedUrlProvider extends AbstractGroupedUrlProvider implements ConfigurableGroupedUrlProvider {

	/**
	 * 当前的分组列表
	 */
	private LinkedList<GroupedUrlProvider> urlProviders;

	public CompositeGroupedUrlProvider() {
	}

	public CompositeGroupedUrlProvider(List<GroupedUrlProvider> urlProviders) {
		this.urlProviders = new LinkedList<>(urlProviders);
	}

	public void setUrlProviders(List<GroupedUrlProvider> urlProviders) {
		this.urlProviders = new LinkedList<>(urlProviders);
	}

	/**
	 * 在末尾新增
	 *
	 * @param urlProvider URL提供器
	 */
	@Override
	public void addLast(GroupedUrlProvider urlProvider) {
		this.urlProviders.addLast(urlProvider);
	}

	/**
	 * 在头部新增
	 *
	 * @param urlProvider URL提供器
	 */
	@Override
	public void addFirst(GroupedUrlProvider urlProvider) {
		this.urlProviders.addFirst(urlProvider);
	}

	/**
	 * 当前的分组索引
	 */
	private int indexGroup;

	/**
	 * 当前分组
	 */
	private GroupedUrlProvider groupedUrlProvider;

	@Override
	protected List<String> urlsGroup() {
		return this.groupedUrlProvider.next().getGroupUrl();
	}

	@Override
	public boolean hasNext() {
		if (null == groupedUrlProvider && indexGroup > urlProviders.size()) {
			return false;
		}

		if (null == groupedUrlProvider) {
			groupedUrlProvider = urlProviders.get(++indexGroup);
			return this.hasNext();
		}


		if (!groupedUrlProvider.hasNext()) {
			while (++indexGroup < urlProviders.size()) {
				groupedUrlProvider = urlProviders.get(indexGroup);
				if (groupedUrlProvider.hasNext()) {
					return true;
				}
			}
			return false;
		}

		return true;
	}
}
