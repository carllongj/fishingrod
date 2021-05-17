package org.carl.rod.config.task;

import org.carl.rod.config.ctl.AbstractHttpParameterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author longjie
 * 2021/5/14
 */
public class DefaultStagedTask extends AbstractHttpParameterTask implements StagedTask {

	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultStagedTask.class);

	/**
	 * 任务列表
	 */
	private List<Task> taskList;

	public DefaultStagedTask() {
	}

	public DefaultStagedTask(List<Task> taskList) {
		this.taskList = taskList;
	}

	public DefaultStagedTask(String taskName) {
		super(taskName);
	}

	public DefaultStagedTask(String taskName, List<Task> taskList) {
		super(taskName);
		this.taskList = taskList;
	}

	@Override
	public List<Task> getStagedTasks() {
		return Collections.unmodifiableList(taskList);
	}
}
