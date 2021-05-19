package org.carl.rod.config.base;

import java.util.List;

/**
 * @author longjie
 * 2021/5/13
 */
public class DefaultConfiguration {

	/**
	 * 公共配置信息
	 */
	private CommonConfiguration common;

	/**
	 * 任务配置记录
	 */
	private List<TaskConfiguration> taskInfo;

	public CommonConfiguration getCommon() {
		return common;
	}

	public void setCommon(CommonConfiguration common) {
		this.common = common;
	}

	public List<TaskConfiguration> getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(List<TaskConfiguration> taskInfo) {
		this.taskInfo = taskInfo;
	}
}
