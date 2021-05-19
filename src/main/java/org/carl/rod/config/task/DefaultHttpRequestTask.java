package org.carl.rod.config.task;

import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.AbstractHttpParameterTask;

/**
 * @author longjie
 * 2021/5/14
 */
public class DefaultHttpRequestTask extends AbstractHttpParameterTask {

	public DefaultHttpRequestTask() {
	}

	public DefaultHttpRequestTask(TaskConfiguration taskConfiguration) {
		super(taskConfiguration);
	}
}
