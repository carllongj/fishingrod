package org.carl.rod.config.ctl;

import org.carl.rod.config.task.Task;

import java.util.List;

/**
 * 任务控制接口定义
 * 包含 时间间隔控制, 输入,输出控制,数据过滤控制
 *
 * @author longjie
 * 2021/5/13
 */
public interface TaskCtl extends Task {

	/**
	 * 获取当前的UrlPathFilter,所有的过滤器
	 *
	 * @return 返回过滤器集合
	 */
	List<UrlPathFilter> getUrlPathFilters();

	/**
	 * 获取当前是否开启了路径过滤
	 *
	 * @return 返回是否开启了uri地址过滤
	 */
	boolean enablePathFilter();

	/**
	 * 新增一个UrlPathFilter过滤器
	 *
	 * @param urlPathFilter 过滤器集合
	 */
	void addUrlPathFilter(UrlPathFilter urlPathFilter);

	/**
	 * 设置当前任务的输入数据处理
	 *
	 * @return 返回当前输入数据如何处理
	 */
	TaskInputHandler getTaskInput();

	/**
	 * 设置当前输入数据的处理器
	 *
	 * @param taskInput 数据输入处理器
	 */
	void setTaskInput(TaskInputHandler taskInput);

	/**
	 * 获取数据的输出处理器
	 *
	 * @return 返回当前的数据输出处理器
	 */
	List<TaskOutputHandler> getTaskOutputHandler();

	/**
	 * 新增数据输出处理器
	 *
	 * @param taskOutputHandler 新增的数据输出处理器
	 */
	void addTaskOutputHandler(TaskOutputHandler taskOutputHandler);

}
