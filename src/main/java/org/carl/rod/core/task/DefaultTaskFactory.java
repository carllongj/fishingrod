package org.carl.rod.core.task;

import org.carl.rod.config.base.HttpRequestConfiguration;
import org.carl.rod.config.base.HttpUrlConfiguration;
import org.carl.rod.config.base.OutputConfiguration;
import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.base.UrlProviderConfiguration;
import org.carl.rod.config.http.PageUrlFileOutputFormatHandler;
import org.carl.rod.config.http.url.CompositeGroupedUrlProvider;
import org.carl.rod.config.http.url.ConfigurationUrlProvider;
import org.carl.rod.config.http.url.FilesUrlProvider;
import org.carl.rod.config.http.url.GroupedUrlProvider;
import org.carl.rod.config.http.url.PageRequestUrlProvider;
import org.carl.rod.config.page.DefaultPageRequestTask;
import org.carl.rod.config.page.HttpPageRequestTask;
import org.carl.rod.config.task.DefaultHttpRequestTask;
import org.carl.rod.config.task.DefaultStagedTask;
import org.carl.rod.config.task.HttpRequestTask;
import org.carl.rod.config.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author longjie
 * 2021/5/13
 */
public class DefaultTaskFactory extends AbstractHttpTaskFactory {

	@Override
	protected Task doCreateTask(TaskConfiguration taskConfig) {
		if (Optional.of(taskConfig).map(TaskConfiguration::getUrlsProvider)
			.map(UrlProviderConfiguration::getHttpUrl).isPresent()) {
			return doCreateStagedTask(taskConfig);
		} else {
			return doCreateSingleTask(taskConfig);
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

		// 获取URL路径配置
		UrlProviderConfiguration urlProviderConfiguration = taskConfig.getUrlsProvider();
		Objects.requireNonNull(urlProviderConfiguration, "url-provider can not be null");

		HttpUrlConfiguration urlConfiguration = urlProviderConfiguration.getHttpUrl();

		TaskConfiguration pageConfiguration = createPageRequestTaskConfiguration(taskConfig);
		//创建分页的任务
		DefaultPageRequestTask pageRequestTask = new DefaultPageRequestTask(pageConfiguration, urlConfiguration.getPageConfig());

		// 设置分页参数信息
		PageRequestUrlProvider pageRequestUrlProvider = new PageRequestUrlProvider();
		pageRequestUrlProvider.setPageStrategy(pageRequestTask.getPageStrategy());
		pageRequestTask.setUrlProvider(pageRequestUrlProvider);

		// 任务数据输出任务
		pageRequestTask.addTaskOutputHandler(new PageUrlFileOutputFormatHandler());

		DefaultHttpRequestTask task = new DefaultHttpRequestTask(taskConfig);
		task.setTaskName(taskName);

		DefaultStagedTask targetTask = new DefaultStagedTask(taskConfig, Arrays.asList(pageRequestTask, task));
		targetTask.setTaskName(taskName);
		targetTask.setHttpMethod(taskConfig.getHttpMethod());


		List<Task> stagedTasks = targetTask.getStagedTasks();
		for (Task childTask : stagedTasks) {
			if (childTask instanceof HttpPageRequestTask) {
				((HttpPageRequestTask) childTask).setParent(targetTask);
			}
		}
		// 创建对应的url提供器
		doCreateUrlProvider(targetTask, urlProviderConfiguration);
		return targetTask;
	}

	private TaskConfiguration createPageRequestTaskConfiguration(TaskConfiguration taskConfig) {
		TaskConfiguration taskConfiguration = new TaskConfiguration();
		HttpUrlConfiguration urlConfiguration = taskConfig.getUrlsProvider().getHttpUrl();
		taskConfiguration.setHttpMethod(urlConfiguration.getHttpMethod());
		if (Objects.nonNull(urlConfiguration.getOutputConfiguration())) {
			taskConfiguration.setOutput(urlConfiguration.getOutputConfiguration());
		} else {
			taskConfiguration.setOutput(generatePageRequestTaskConfiguration(taskConfig));
		}
		return taskConfiguration;
	}

	private OutputConfiguration generatePageRequestTaskConfiguration(TaskConfiguration taskConfiguration) {
		OutputConfiguration configuration = new OutputConfiguration();
		configuration.setFileName(taskConfiguration.getOutput().getFileName());
		configuration.setCharset(taskConfiguration.getOutput().getCharset());
		configuration.setPath(taskConfiguration.getOutput().getPath());
		configuration.setSuffix("url");
		return configuration;
	}

	/**
	 * 创建默认的URLProvider
	 *
	 * @param task                     当前的任务
	 * @param urlProviderConfiguration urlProvider 配置项
	 */
	private void doCreateUrlProvider(Task task, UrlProviderConfiguration urlProviderConfiguration) {
		if (task instanceof HttpRequestTask) {
			List<GroupedUrlProvider> providers = new ArrayList<>();
			if (Objects.nonNull(urlProviderConfiguration.getUrls()) && urlProviderConfiguration.getUrls().size() > 0) {
				providers.add(new ConfigurationUrlProvider(urlProviderConfiguration.getUrls()));
			}
			if (Objects.nonNull(urlProviderConfiguration.getFiles()) && urlProviderConfiguration.getFiles().size() > 0) {
				providers.add(new FilesUrlProvider(urlProviderConfiguration.getFiles()));
			}
			((HttpRequestTask) task).setUrlProvider(new CompositeGroupedUrlProvider(providers));
		}
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
		// 创建对应的UrlProvider
		doCreateUrlProvider(requestTask, taskConfig.getUrlsProvider());
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
