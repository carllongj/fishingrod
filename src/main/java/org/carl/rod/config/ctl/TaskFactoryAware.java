package org.carl.rod.config.ctl;

import org.carl.rod.core.task.HttpTaskFactory;

/**
 * @author longjie
 * 2021/5/17
 */
public interface TaskFactoryAware {

	/**
	 * 设置任务工厂信息
	 *
	 * @param taskFactory 任务工厂
	 */
	void setTaskFactory(HttpTaskFactory taskFactory);

}
