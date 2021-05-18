package org.carl.rod.config.ctl;

/**
 * @author longjie
 * 2021/5/13
 */
public interface TaskInputHandler {

	/**
	 * 执行输入数据的处理
	 *
	 * @param taskInput 当前任务的输入数据(Http请求发送)
	 * @return 返回处理后的结果
	 */
	Object handle(Document taskInput);
}
