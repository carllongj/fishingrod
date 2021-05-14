package org.carl.rod.core.task;

import org.carl.rod.config.base.RodBaseConfiguration;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.name.TaskNameGenerator;

import java.util.List;

/**
 * @author longjie
 * 2021/5/13
 */
public interface TaskFactory {

	/**
	 * 名称生成器
	 *
	 * @param taskNameGenerator 对应的名称生成器
	 */
	void setTaskNameGenerator(TaskNameGenerator taskNameGenerator);

	/**
	 * 获取名称生成器
	 *
	 * @return 返回名称生成器
	 */
	TaskNameGenerator getTaskNameGenerator();

	/**
	 * 创建任务
	 *
	 * @param configuration 所有的配置项
	 * @return 返回创建完成的所有task集合
	 */
	List<Task> createTask(RodBaseConfiguration configuration);

	/**
	 * 添加任务处理器
	 *
	 * @param processor 添加任务处理器
	 */
	void addTaskPostProcessor(TaskPostProcessor processor);
}
