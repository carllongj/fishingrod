package org.carl.rod.core.task;

import org.carl.rod.config.base.RodBaseConfiguration;
import org.carl.rod.config.ctl.Document;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.http.doc.DocumentCreator;
import org.carl.rod.core.http.handlers.HttpFinishedHandler;
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

	/**
	 * 获取当前所有的任务处理器
	 *
	 * @return 返回所有的任务处理器
	 */
	List<TaskPostProcessor> getTaskPostProcessor();

	/**
	 * 新增HttpFinishedHandler
	 *
	 * @param finishedHandler 新增HttpFinishedHandler
	 */
	void addFinishedHandler(HttpFinishedHandler finishedHandler);

	/**
	 * 获取所有的任务执行处理器
	 *
	 * @return 返回所有的处理器
	 */
	List<HttpFinishedHandler> getHttpFinishedHandler();

	/**
	 * 新增一个文档创建器
	 *
	 * @param documentCreator 文档创建
	 */
	void addDocumentCreator(DocumentCreator documentCreator);

	/**
	 * 进行创建对应的文档
	 *
	 * @param source 目标源对象
	 * @return 返回创建完成的对象
	 */
	Document createDocument(Object source);
}
