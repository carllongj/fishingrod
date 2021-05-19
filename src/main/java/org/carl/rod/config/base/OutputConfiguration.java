package org.carl.rod.config.base;

import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author longjie
 * 2021/5/18
 */
public class OutputConfiguration {

	/**
	 * 存储路径
	 */
	private String path;

	/**
	 * 文件后缀名称
	 */
	private String suffix;

	/**
	 * 输出的文件名称
	 */
	private String fileName;

	/**
	 * 设置输出的文本编码格式
	 */
	private String charset;

	public OutputConfiguration() {
	}

	public OutputConfiguration(OutputConfiguration outputConfiguration) {
		this.path = outputConfiguration.getPath();
		this.suffix = outputConfiguration.getSuffix();
		this.fileName = outputConfiguration.getFileName();
		this.charset = outputConfiguration.getCharset();
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 默认配置对象
	 */
	public static final OutputConfiguration DEFAULT_OUTPUT_CONFIGURATION = InnerOutputConfiguration.INSTANCE;

	private static class InnerOutputConfiguration {

		private static final OutputConfiguration INSTANCE = new OutputConfiguration();

		static {
			INSTANCE.path = Paths.get(Optional.ofNullable(System.getProperty("rod.save.path"))
				.orElse(System.getProperty("user.name"))).toString();
			INSTANCE.charset = "UTF-8";
			INSTANCE.suffix = "record";
		}
	}
	/**
	 * 创建默认的配置对象
	 */

}
