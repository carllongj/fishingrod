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

package org.carl.rod.config.base;

import java.util.List;

/**
 * URL 地址链接获取
 *
 * @author carllongj
 * 2021/5/20 12:49
 */
public class UrlProviderConfiguration {

	/**
	 * 配置的配置文件中的url地址
	 */
	private List<String> urls;

	/**
	 * 配置外部文件的url列表集合
	 */
	private List<String> files;

	/**
	 * 用以配置通过抓取Http页面的配置信息
	 */
	private HttpUrlConfiguration httpUrl;

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	public HttpUrlConfiguration getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(HttpUrlConfiguration httpUrl) {
		this.httpUrl = httpUrl;
	}
}
