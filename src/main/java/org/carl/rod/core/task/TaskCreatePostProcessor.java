package org.carl.rod.core.task;

import org.carl.rod.config.base.TaskConfiguration;

/**
 * @author longjie
 * 2021/5/13
 */
public interface TaskCreatePostProcessor extends TaskPostProcessor {

	/**
	 * 执行创建对应的Task任务之前,修改和配置当前的TaskConfiguration
	 *
	 * @param taskFactory   任务创建工厂
	 * @param configuration 创建任务参数信息
	 */
	void beforeCreateTask(TaskFactory taskFactory, TaskConfiguration configuration);
}
