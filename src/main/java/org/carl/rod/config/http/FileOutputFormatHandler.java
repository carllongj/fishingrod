package org.carl.rod.config.http;

import org.carl.rod.config.ctl.TaskOutputHandler;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

/**
 * @author longjie
 * 2021/5/18
 */
public class FileOutputFormatHandler implements TaskOutputHandler {

	private static final char SEPARATOR = '\t';

	private static final char KEY_VALUE_SEPARATOR = '\u0009';

	/**
	 * 当前输出路径
	 */
	private String path;

	/**
	 * 记录当前的任务名称
	 */
	private String taskName;

	/**
	 * 目标的suffix
	 */
	private String suffix;

	public FileOutputFormatHandler(String path, String taskName, String suffix) {
		this.path = path;
		this.taskName = taskName;
		this.suffix = suffix;
	}

	@Override
	public boolean isSupport(Object target) {
		return target instanceof HttpMappedValue &&
			((HttpMappedValue) target).getTaskName().equals(taskName);
	}

	@Override
	public void handleOutput(Object target) throws Exception {
		HttpMappedValue result = (HttpMappedValue) target;
		Map<String, String> extractMap = result.getExtractMap();
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : extractMap.entrySet()) {
			builder.append(entry.getKey()).append(KEY_VALUE_SEPARATOR).append(entry.getValue()).append(SEPARATOR);
		}
		Files.write(Paths.get(path, taskName + suffix), builder.toString().getBytes(), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
	}
}
