package org.carl.rod.config.ctl;

import org.carl.rod.core.task.TaskFactory;

/**
 * @author longjie
 * 2021/5/17
 */
public interface TaskFactoryAware {

	void setTaskFactory(TaskFactory taskFactory);

}
