package org.carl.rod.core.advice;

import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.base.UrlProviderConfiguration;
import org.carl.rod.config.ctl.TaskCtl;
import org.carl.rod.config.http.HttpMappedValueTaskInputHandler;
import org.carl.rod.config.http.JsonFormatOutputHandler;
import org.carl.rod.config.http.PageUrlFileOutputFormatHandler;
import org.carl.rod.config.http.url.CompositeGroupedUrlProvider;
import org.carl.rod.config.http.url.ConfigurationUrlProvider;
import org.carl.rod.config.http.url.FilesUrlProvider;
import org.carl.rod.config.http.url.GroupedUrlProvider;
import org.carl.rod.config.http.url.PageRequestUrlProvider;
import org.carl.rod.config.page.DefaultPageRequestTask;
import org.carl.rod.config.page.HttpPageRequestTask;
import org.carl.rod.config.page.PageFormatStrategy;
import org.carl.rod.config.task.DefaultStagedTask;
import org.carl.rod.config.task.HttpRequestTask;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.task.TaskPostProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/18
 */
public class HttpMappedValueSupportAdvice implements TaskPostProcessor {

	@Override
	public Task postProcess(Task task, TaskConfiguration taskConfiguration) {
		// 获取URL路径配置
		UrlProviderConfiguration urlProviderConfiguration = task.getTaskConfiguration().getUrlProvider();
		Objects.requireNonNull(urlProviderConfiguration, "url-provider can not be null");
		if (Objects.nonNull(urlProviderConfiguration.getConfig())) {
			// TODO: 2021/5/20  创建对应的链接获取,若当前通过pageConfig获取,则进行创建 StagedTask对应的实例
			// TODO: 2021/5/21 目前先如此实现,后续在修改此处的过于耦合代码

			DefaultPageRequestTask pageRequestTask = new DefaultPageRequestTask(urlProviderConfiguration.getConfig());
			pageRequestTask.addTaskOutputHandler(new PageUrlFileOutputFormatHandler());
			PageRequestUrlProvider urlProvider = new PageRequestUrlProvider();
			urlProvider.setPageStrategy(new PageFormatStrategy(urlProviderConfiguration.getConfig()));
			pageRequestTask.setUrlProvider(urlProvider);
			DefaultStagedTask targetTask = new DefaultStagedTask(Arrays.asList(pageRequestTask, task));
			List<Task> stagedTasks = targetTask.getStagedTasks();
			for (Task childTask : stagedTasks) {
				if (childTask instanceof HttpPageRequestTask) {
					((HttpPageRequestTask) childTask).setParent(targetTask);
				}
			}
			task = targetTask;
		}

		// 创建对应的url提供器
		doCreateUrlProvider(task, urlProviderConfiguration);

		if (task instanceof TaskCtl) {
			setOutputConfig((TaskCtl) task, taskConfiguration);

			if (null != taskConfiguration.getSelector()) {
				HttpMappedValueTaskInputHandler taskInputHandler = new HttpMappedValueTaskInputHandler();
				taskInputHandler.setSelectors(taskConfiguration.getSelector());
				((TaskCtl) task).setTaskInput(taskInputHandler);
			}
		}

		return task;
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
			if (Objects.nonNull(urlProviderConfiguration) && urlProviderConfiguration.getUrls().size() > 0) {
				providers.add(new ConfigurationUrlProvider(urlProviderConfiguration.getUrls()));
			}
			if (Objects.nonNull(urlProviderConfiguration.getFiles()) && urlProviderConfiguration.getFiles().size() > 0) {
				providers.add(new FilesUrlProvider(urlProviderConfiguration.getFiles()));
			}
			((HttpRequestTask) task).setUrlProvider(new CompositeGroupedUrlProvider(providers));
		}
	}

	/**
	 * 设置输出配置信息
	 *
	 * @param task              目标任务
	 * @param taskConfiguration 任务配置信息
	 */
	private void setOutputConfig(TaskCtl task, TaskConfiguration taskConfiguration) {
		if (null != taskConfiguration.getOutput()) {
			task.addTaskOutputHandler(new JsonFormatOutputHandler());
		}
	}
}
