package org.carl.rod.config.http;

import org.carl.rod.config.base.OutputConfiguration;
import org.carl.rod.config.task.HttpRequestTask;

import java.io.IOException;

/**
 * @author longjie
 * 2021/5/31
 */
public interface OutputHandler {

	/**
	 * 执行最终的数据输出
	 *
	 * @param requestTask         任务
	 * @param formatLine          格式化完成的数据
	 * @param outputConfiguration 输出配置
	 */
	void doOutput(HttpRequestTask requestTask, String formatLine, OutputConfiguration outputConfiguration) throws IOException;
}
