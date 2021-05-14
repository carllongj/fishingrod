package org.carl.rod.core.name;

/**
 * @author longjie
 * 2021/5/14
 */
public interface TaskNameGenerator {

	/**
	 * 创建当前的任务名称
	 *
	 * @return 返回对应的任务名称
	 */
	String generateTaskName();

}
