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

	/**
	 * 创建分页抓取和数据抓取的任务
	 *
	 * @param taskConfig 任务配置
	 * @return 返回对应的任务信息
	 */
	protected Task doCreateStagedTask(TaskConfiguration taskConfig) {
		String taskName;
		if (Objects.nonNull(taskConfig.getTaskName())) {
			taskName = taskConfig.getTaskName();
		} else {
			taskName = this.getTaskNameGenerator().generateTaskName();
		}

		DefaultPageRequestTask pageRequestTask
			= new DefaultPageRequestTask(taskConfig, taskConfig.getPageConfig());
		pageRequestTask.setTaskName(taskName);
		pageRequestTask.setHttpMethod(taskConfig.getHttpMethod());
		DefaultHttpRequestTask requestTask = new DefaultHttpRequestTask(taskConfig);
		requestTask.setHttpMethod(taskConfig.getHttpMethod());
		DefaultStagedTask stagedTask = new DefaultStagedTask(taskConfig, Arrays.asList(pageRequestTask, requestTask));
		stagedTask.setTaskName(taskName);
		return stagedTask;
	}

	/**
	 * 创建单个默认任务
	 *
	 * @param taskConfig 任务配置信息
	 * @return 返回创建完成的任务
	 */
	protected Task doCreateSingleTask(TaskConfiguration taskConfig) {
		String taskName;
		if (Objects.isNull(taskConfig.getTaskName())) {
			taskName = this.getTaskNameGenerator().generateTaskName();
		} else {
			taskName = taskConfig.getTaskName();
		}
		DefaultHttpRequestTask requestTask = new DefaultHttpRequestTask(taskConfig);
		requestTask.setTaskName(taskName);
		requestTask.setHttpMethod(taskConfig.getHttpMethod());
		// 获取http的配置
		if (null != taskConfig.getHttpConfig()) {
			HttpRequestConfiguration httpConfig = taskConfig.getHttpConfig();
			//请求头配置
			if (null != httpConfig.getHeaders()) {
				for (Map.Entry<String, List<String>> entry : httpConfig.getHeaders().entrySet()) {
					for (String value : entry.getValue()) {
						requestTask.addRequestHeader(entry.getKey(), value);
					}
				}
			}

			// 请求参数配置
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
