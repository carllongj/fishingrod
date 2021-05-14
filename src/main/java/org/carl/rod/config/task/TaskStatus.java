package org.carl.rod.config.task;

/**
 * @author longjie
 * 2021/5/14
 */
public enum TaskStatus {

	/**
	 * 任务执行成功
	 */
	SUCCESS(0),

	/**
	 * 任务执行失败
	 */
	FAILED(1),

	/**
	 * 任务执行中
	 */
	EXECUTING(2),

	/**
	 * 任务未执行
	 */
	NOT_EXECUTE(3);

	/**
	 * 状态编码
	 */
	private Integer code;

	TaskStatus(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	/**
	 * 根据状态码获取对应的状态信息
	 *
	 * @param code 状态码
	 * @return 状态信息
	 */
	public static TaskStatus valueOf(int code) {
		for (TaskStatus status : values()) {
			if (status.code == code) {
				return status;
			}
		}
		return null;
	}
}
