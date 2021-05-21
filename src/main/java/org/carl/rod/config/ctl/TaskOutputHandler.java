package org.carl.rod.config.ctl;

import org.carl.rod.config.task.HttpRequestTask;

/**
 * @author longjie
 * 2021/5/13
 */
public interface TaskOutputHandler {

	/**
	 * 判断是否支持处理当前处理数据对象
	 *
	 * @param target 目标对象
	 * @return 返回是否支持
	 */
	boolean isSupport(Object target);

	/**
	 * 执行数据的输出处理
	 *
	 * @param task   当前任务
	 * @param target 输入数据的处理结果
	 */
	void handleOutput(HttpRequestTask task, Object target) throws Exception;
}
