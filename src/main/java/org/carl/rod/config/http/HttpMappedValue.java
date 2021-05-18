package org.carl.rod.config.http;

import java.util.Map;

/**
 * @author longjie
 * 2021/5/18
 */
public class HttpMappedValue {

	/**
	 * 记录当前的任务名称
	 */
	private String taskName;

	/**
	 * 提取完成的数据结果记录
	 */
	private Map<String, String> extractMap;

	public HttpMappedValue() {
	}

	public HttpMappedValue(String taskName, Map<String, String> extractMap) {
		this.taskName = taskName;
		this.extractMap = extractMap;
	}

	public Map<String, String> getExtractMap() {
		return extractMap;
	}

	public void setExtractMap(Map<String, String> extractMap) {
		this.extractMap = extractMap;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
