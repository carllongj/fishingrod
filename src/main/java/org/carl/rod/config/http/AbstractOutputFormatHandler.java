package org.carl.rod.config.http;

import org.carl.rod.config.base.OutputConfiguration;
import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.TaskOutputHandler;
import org.carl.rod.config.task.HttpRequestTask;

import java.io.IOException;

/**
 * @author longjie
 * 2021/5/18
 */
public abstract class AbstractOutputFormatHandler implements TaskOutputHandler {

	/**
	 * 输出处理器
	 */
	private OutputHandler outputHandler;

	public AbstractOutputFormatHandler() {
	}

	public void setOutputHandler(OutputHandler outputHandler) {
		this.outputHandler = outputHandler;
	}

	@Override
	public boolean isSupport(Object target) {
		return target instanceof HttpMappedValue;
	}

	@Override
	public final void handleOutput(HttpRequestTask requestTask, Object target) throws Exception {
		HttpMappedValue result = (HttpMappedValue) target;

		// 格式化对应的数据
		String formatLine = formatLine(result);

		//获取任务配置信息
		TaskConfiguration taskConfiguration = result.getRequestTask().getTaskConfiguration();

		// 获取输出配置信息
		// TODO: 2021/5/27 处理子任务会往父级别输出定义中输出结果
		OutputConfiguration output = taskConfiguration.getOutput();

		//执行输出
		doOutput(requestTask, formatLine, output);
	}

	/**
	 * 执行输出内容
	 *
	 * @param formatLine          格式化完成的文本内容
	 * @param outputConfiguration 输出配置信息
	 */
	protected void doOutput(HttpRequestTask requestTask, String formatLine, OutputConfiguration outputConfiguration) throws IOException {
		this.outputHandler.doOutput(requestTask, formatLine, outputConfiguration);
	}

	/**
	 * 格式化当前的结果
	 *
	 * @param result 结果内容
	 * @return 返回格式化后的内容
	 */
	protected abstract String formatLine(HttpMappedValue result);
}
