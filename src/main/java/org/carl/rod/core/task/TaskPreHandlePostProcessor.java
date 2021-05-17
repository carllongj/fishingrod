package org.carl.rod.core.task;

import org.carl.rod.config.task.Task;
import org.carl.rod.core.http.HttpUriRequestWrapper;

/**
 * @author longjie
 * 2021/5/17
 */
public interface TaskPreHandlePostProcessor extends TaskPostProcessor {

	/**
	 * 任务执行前执行的操作
	 *
	 * @param task           当前的任务
	 * @param requestWrapper 请求包装对象
	 */
	void preHandle(Task task, HttpUriRequestWrapper requestWrapper);
}
