package org.carl.rod.core.task.definition;

/**
 * @author longjie
 * 2021/5/21
 */
public interface HttpTaskDefinitionRegistry {

	/**
	 * 注册任务的 TaskDefinition
	 *
	 * @param taskDefinition 注册任务
	 */
	void registerTaskDefinition(HttpTaskDefinition taskDefinition);
}
