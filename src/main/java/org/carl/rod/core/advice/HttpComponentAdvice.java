package org.carl.rod.core.advice;

import org.apache.http.impl.client.CloseableHttpClient;
import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.task.HttpRequestTask;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.task.TaskPostProcessor;

/**
 * @author longjie
 * 2021/5/14
 */
public class HttpComponentAdvice implements TaskPostProcessor {

	/**
	 * 设置当前的httpHttpComponent
	 */
	private CloseableHttpClient httpClient;

	@Override
	public Task postProcess(Task task, TaskConfiguration taskConfiguration) {
		if (task instanceof HttpRequestTask) {
			((HttpRequestTask) task).setHttpComponent(httpClient);
		}
		return task;
	}
}
