package org.carl.rod.config.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longjie
 * 2021/5/13
 */
public class HttpRequestConfiguration {
	/**
	 * 公共请求头
	 */
	private Map<String, List<String>> headers;

	/**
	 * 公共参数
	 */
	private Map<String, List<String>> parameters;

	public void addHttpRequestConfiguration(HttpRequestConfiguration requestConfiguration) {
		if (null == requestConfiguration) {
			return;
		}

		if (null == headers) {
			headers = new LinkedHashMap<>();
		}

		if (null == parameters) {
			parameters = new LinkedHashMap<>();
		}

		mergeKeyValues(headers, requestConfiguration.getHeaders());
		mergeKeyValues(parameters, requestConfiguration.getParameters());
	}

	/**
	 * 合并配置项
	 *
	 * @param origin 当前返回配置项
	 * @param merge  将要合并的配置项
	 * @return 返回合并后的原始配置项
	 */
	private Map<String, List<String>> mergeKeyValues(Map<String, List<String>> origin, Map<String, List<String>> merge) {
		if (null == merge || merge.isEmpty()) {
			return origin;
		}
		for (Map.Entry<String, List<String>> entry : merge.entrySet()) {
			if (entry.getValue() == null || entry.getValue().isEmpty()) {
				continue;
			}

			if (origin.containsKey(entry.getKey())) {
				origin.get(entry.getKey()).addAll(entry.getValue());
			} else {
				List<String> headerValueList = new ArrayList<>();
				origin.put(entry.getKey(), headerValueList);
				headerValueList.addAll(entry.getValue());
			}
		}
		return origin;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public Map<String, List<String>> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, List<String>> parameters) {
		this.parameters = parameters;
	}
}
