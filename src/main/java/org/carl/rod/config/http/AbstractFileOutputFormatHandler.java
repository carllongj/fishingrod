package org.carl.rod.config.http;

import org.apache.commons.codec.Charsets;
import org.carl.rod.config.base.OutputConfiguration;
import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.ctl.TaskOutputHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author longjie
 * 2021/5/18
 */
public abstract class AbstractFileOutputFormatHandler implements TaskOutputHandler {

	/**
	 * 后缀名称分隔符
	 */
	private static final char FILE_SUFFIX_SEPARATOR = '.';

	public AbstractFileOutputFormatHandler() {
	}

	@Override
	public boolean isSupport(Object target) {
		return target instanceof HttpMappedValue;
	}

	@Override
	public void handleOutput(Object target) throws Exception {
		HttpMappedValue result = (HttpMappedValue) target;

		// 格式化对应的数据
		String formatLine = formatLine(result);

		//获取任务配置信息
		TaskConfiguration taskConfiguration = result.getRequestTask().getTaskConfiguration();

		// 获取输出配置信息
		OutputConfiguration output = taskConfiguration.getOutput();

		//执行输出
		doOutput(formatLine, output);
	}

	/**
	 * 执行输出内容
	 *
	 * @param formatLine          格式化完成的文本内容
	 * @param outputConfiguration 输出配置信息
	 */
	protected void doOutput(String formatLine, OutputConfiguration outputConfiguration) throws IOException {
		Path path = this.buildOutputPath(outputConfiguration.getPath(), outputConfiguration.getFileName(), outputConfiguration.getSuffix());
		Files.write(path, formatLine.getBytes(Charsets.toCharset(outputConfiguration.getCharset())),
			StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE);
	}

	protected abstract String formatLine(HttpMappedValue result);

	/**
	 * 构建对应的文件名称
	 *
	 * @param path     指定的路径地址
	 * @param fileName 文件名称
	 * @param suffix   文件后缀名称
	 * @return 返回对应的Path路径
	 */
	protected Path buildOutputPath(String path, String fileName, String suffix) {
		return Paths.get(path, fileName + FILE_SUFFIX_SEPARATOR + suffix);
	}
}
