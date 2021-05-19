package org.carl.rod.config.ctl;

import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.task.Task;

import java.util.Objects;

/**
 * @author longjie
 * 2021/5/13
 */
public abstract class AbstractTask implements Task {

	private String taskName;

	private TaskConfiguration taskConfiguration;

	public AbstractTask() {
	}

	public AbstractTask(TaskConfiguration taskConfiguration) {
		this.taskConfiguration = taskConfiguration;
		this.taskName = taskConfiguration.getTaskName();
	}

	@Override
	public String getTaskName() {
		if (Objects.isNull(this.taskName)) {
			this.taskName = this.taskConfiguration.getTaskName();
		}
		return this.taskName;
	}

	@Override
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public TaskConfiguration getTaskConfiguration() {
		return this.taskConfiguration;
	}
}
