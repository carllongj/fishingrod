package org.carl.rod.config.base;

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
}
