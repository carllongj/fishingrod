package org.carl.rod.config.task;

import java.util.List;

/**
 * 阶段任务,一个完整的任务是由多个任务组成
 *
 * @author longjie
 * 2021/5/13
 */
public interface StagedTask extends Task {

	/**
	 * 阶段任务名称描述
	 */
	String STAGED_TASK_PREFIX = "stagedTask";

	/**
	 * 获取当前的步骤任务
	 *
	 * @return 返回当前任务集合
	 */
	List<Task> getStagedTasks();
}
