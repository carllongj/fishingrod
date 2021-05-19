package org.carl.rod.config.http;

import org.carl.rod.config.task.Task;
import org.carl.rod.config.task.TaskAware;

import java.util.Map;

/**
 * @author longjie
 * 2021/5/18
 */
public class HttpMappedValue implements TaskAware {

	/**
	 * 当前的任务
	 */
	private Task requestTask;

	/**
	 * 提取完成的数据结果记录
	 */
	private Map<String, String> extractMap;

	public HttpMappedValue() {
	}

	public HttpMappedValue(Map<String, String> extractMap) {
		this.extractMap = extractMap;
	}

	public Map<String, String> getExtractMap() {
		return extractMap;
	}

	public void setExtractMap(Map<String, String> extractMap) {
		this.extractMap = extractMap;
	}

	public Task getRequestTask() {
		return requestTask;
	}

	@Override
	public void setTask(Task task) {
		this.requestTask = task;
	}
}
