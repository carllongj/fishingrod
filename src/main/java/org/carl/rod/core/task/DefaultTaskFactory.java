package org.carl.rod.core.task;

import org.carl.rod.config.base.HttpRequestConfiguration;
import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.page.DefaultPageRequestTask;
import org.carl.rod.config.task.DefaultHttpRequestTask;
import org.carl.rod.config.task.DefaultStagedTask;
import org.carl.rod.config.task.PageInfo;
import org.carl.rod.config.task.Task;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/13
 */
public class DefaultTaskFactory extends AbstractTaskFactory {

	@Override
	protected Task doCreateTask(TaskConfiguration taskConfig) {
		PageInfo pageConfig = taskConfig.getPageConfig();
		if (null == pageConfig) {
			return doCreateSingleTask(taskConfig);
		} else {
			return doCreateStagedTask(taskConfig);
		}
	}


	protected Task doCreateStagedTask(TaskConfiguration taskConfig) {
		String taskName;
		if (Objects.nonNull(taskConfig.getTaskName())) {
			taskName = taskConfig.getTaskName();
		} else {
			taskName = this.getTaskNameGenerator().generateTaskName();
		}

		DefaultPageRequestTask pageRequestTask
			= new DefaultPageRequestTask(this.getTaskNameGenerator().generateTaskName(), taskConfig.getPageConfig());
		pageRequestTask.setUrl(taskConfig.getUrl());
		pageRequestTask.setHttpMethod(taskConfig.getHttpMethod());
		DefaultHttpRequestTask requestTask = new DefaultHttpRequestTask(this.getTaskNameGenerator().generateTaskName());
		requestTask.setUrl(taskConfig.getUrl());
		requestTask.setHttpMethod(taskConfig.getHttpMethod());
		return new DefaultStagedTask(taskName, Arrays.asList(pageRequestTask, requestTask));
	}

	/**
	 * 创建单个默认任务
	 *
	 * @param taskConfig 任务配置信息
	 * @return 返回创建完成的任务
	 */
	protected Task doCreateSingleTask(TaskConfiguration taskConfig) {
		String taskName;
		if (Objects.nonNull(taskConfig.getTaskName())) {
			taskName = taskConfig.getTaskName();
		} else {
			taskName = this.getTaskNameGenerator().generateTaskName();
		}
		DefaultHttpRequestTask requestTask = new DefaultHttpRequestTask(taskName);
		requestTask.setUrl(taskConfig.getUrl());
		requestTask.setHttpMethod(taskConfig.getHttpMethod());
		if (null != taskConfig.getHttpConfig()) {
			HttpRequestConfiguration httpConfig = taskConfig.getHttpConfig();

			if (null != httpConfig.getHeaders()) {
				for (Map.Entry<String, List<String>> entry : httpConfig.getHeaders().entrySet()) {
					for (String value : entry.getValue()) {
						requestTask.addRequestHeader(entry.getKey(), value);
					}
				}
			}

			if (null != httpConfig.getParameters()) {
				for (Map.Entry<String, List<String>> entry : httpConfig.getParameters().entrySet()) {
					for (String value : entry.getValue()) {
						requestTask.addRequestHeader(entry.getKey(), value);
					}
				}
			}
		}
		return requestTask;
	}
}
