package org.carl.rod.utils;

import java.util.Objects;

/**
 * @author longjie
 * 2021/5/27
 */
public abstract class PathUtils {

	/**
	 * 路径分隔符
	 */
	public static final char PATH_SEPARATOR = '/';

	/**
	 * 父级别目录
	 */
	public static final String parentPath = "../";

	/**
	 * 当前目录
	 */
	public static final String currentPath = "./";

	/**
	 * 构建对应的路径地址
	 *
	 * @param parent 跟路径地址
	 * @param child  子路径地址
	 * @return 返回最后完整路径
	 */
	public static String get(String parent, String... child) {
		if (Objects.isNull(child) || child.length == 0) {
			return parent;
		}
		StringBuilder sb;
		if (Objects.isNull(parent) || parent.length() == 0) {
			sb = new StringBuilder();
		} else {
			sb = new StringBuilder(parent);
		}

		for (String childPath : child) {
			if (Objects.isNull(childPath) || childPath.isEmpty()) {
				continue;
			}

			if (sb.length() == 0) {
				sb.append(childPath);
			}

			if (sb.charAt(sb.length() - 1) == PATH_SEPARATOR &&
				childPath.charAt(0) == PATH_SEPARATOR) {
				sb.append(childPath.substring(1));
			} else if (sb.charAt(sb.length() - 1) != PATH_SEPARATOR && childPath.charAt(0) != PATH_SEPARATOR) {
				sb.append(PATH_SEPARATOR).append(childPath);
			} else {
				sb.append(childPath);
			}
		}
		return sb.toString();
	}
}
