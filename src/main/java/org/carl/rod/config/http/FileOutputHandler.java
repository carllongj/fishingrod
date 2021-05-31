package org.carl.rod.config.http;

import org.apache.commons.codec.Charsets;
import org.carl.rod.config.base.OutputConfiguration;
import org.carl.rod.config.http.url.FilesUrlProvider;
import org.carl.rod.config.page.HttpPageRequestTask;
import org.carl.rod.config.task.HttpRequestTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

/**
 * @author longjie
 * 2021/5/31
 */
public class FileOutputHandler implements OutputHandler {

	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileOutputHandler.class);

	/**
	 * 后缀名称分隔符
	 */
	private static final char FILE_SUFFIX_SEPARATOR = '.';

	@Override
	public void doOutput(HttpRequestTask requestTask, String formatLine, OutputConfiguration outputConfiguration) throws IOException {
		// 当前任务的输出路径地址
		Path path = this.buildOutputPath(outputConfiguration.getPath(), outputConfiguration.getFileName(), outputConfiguration.getSuffix());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("task {} write output in file {}", requestTask.getTaskName(), path.toString());
		}
		// 执行数据写出
		Files.write(path, formatLine.getBytes(Charsets.toCharset(outputConfiguration.getCharset())),
			StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE);

		// 设置任务的路径提供器
		if (null != requestTask.getParent() &&
			requestTask instanceof HttpPageRequestTask &&
			requestTask.getParent() instanceof HttpRequestTask) {
			((HttpRequestTask) requestTask.getParent()).addLast(
				new FilesUrlProvider(Collections.singletonList(path.toString())));
		}
	}

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
