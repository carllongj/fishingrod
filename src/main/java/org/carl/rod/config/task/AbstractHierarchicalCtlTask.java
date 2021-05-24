/*
 * Copyright 2021 carllongj
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package org.carl.rod.config.task;

import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.AbstractCtlTask;
import org.carl.rod.config.ctl.TaskCtl;
import org.carl.rod.config.ctl.TaskInputHandler;
import org.carl.rod.config.ctl.TaskOutputHandler;

import java.util.List;
import java.util.Objects;

/**
 * @author carllongj
 * 2021/5/24 19:37
 */
public abstract class AbstractHierarchicalCtlTask extends AbstractCtlTask implements HierarchicalTask {

	public AbstractHierarchicalCtlTask() {
		super();
	}

	public AbstractHierarchicalCtlTask(TaskConfiguration taskConfiguration) {
		super(taskConfiguration);
	}

	@Override
	public TaskInputHandler getTaskInput() {
		if (Objects.isNull(super.getTaskInput()) && Objects.nonNull(this.getParent())) {
			return this.getParent() instanceof TaskCtl ? ((TaskCtl) this.getParent()).getTaskInput() : null;
		}
		return super.getTaskInput();
	}

	@Override
	public List<TaskOutputHandler> getTaskOutputHandler() {
		if (Objects.isNull(super.getTaskOutputHandler()) && Objects.nonNull(this.getParent())) {
			return this.getParent() instanceof TaskCtl ? ((TaskCtl) this.getParent()).getTaskOutputHandler() : null;
		}
		return super.getTaskOutputHandler();
	}

	@Override
	public TaskConfiguration getTaskConfiguration() {
		if (Objects.isNull(super.getTaskConfiguration()) && Objects.nonNull(this.getParent())) {
			return this.getParent().getTaskConfiguration();
		}

		return super.getTaskConfiguration();
	}
}
