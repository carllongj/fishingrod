package org.carl.rod.utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author longjie
 * 2021/5/10
 */
public abstract class StringUtils {

	/**
	 * 空字符串
	 */
	public static final String EMPTY = "";

	/**
	 * 换行符
	 */
	public static final char LINE_SEPARATOR = '\n';

	/**
	 * 中画线分隔符
	 */
	private static final char STRIKE_THROUGH = '-';

	/**
	 * 判断两个字符串是否相同,
	 * 若两个字符串同时为null或者空字符串,也将返回<code>true</code>
	 *
	 * @param source 源字符串
	 * @param target 目标字符串
	 * @return 返回是否相同
	 */
	public static boolean equalsIgnoreCase(String source, String target) {
		if (Objects.equals(source, target)) {
			return true;
		}

		if (Objects.isNull(source) || Objects.isNull(target)) {
			return false;
		}

		return source.equalsIgnoreCase(target);
	}

	/**
	 * 将目标字符串替换成中划线风格字符串
	 *
	 * @param origin 原始字符串
	 * @return 返回驼峰字符串
	 */
	public static String replaceHumpStyleToStrikeThrough(String origin) {
		if (Objects.isNull(origin) || origin.isEmpty()) {
			return origin;
		}
		// 默认添加 StringBuilder 的length
		StringBuilder sb = new StringBuilder(origin.length() + 32);
		int index = 0;
		for (int i = 0; i < origin.length(); i++) {
			if (Character.isUpperCase(origin.charAt(i))) {
				sb.append(origin, index, i).append(STRIKE_THROUGH);
				sb.append(Character.toLowerCase(origin.charAt(i)));
				index = i + 1;
			}
		}

		if (index < origin.length()) {
			sb.append(origin, index, origin.length());
		}

		return sb.toString();
	}

	/**
	 * 提取根请求链接地址
	 */
	private static final Pattern HTTP_REQUEST_TEMPLATE = Pattern.compile("(https?://.*?/)");

	/**
	 * 提取Http请求根路径
	 *
	 * @param requestUrl 请求路径
	 * @return 返回根路径
	 */
	public static String extractHttpBaseUrl(String requestUrl) {
		if (null == requestUrl || requestUrl.isEmpty()) {
			return null;
		}

		Matcher matcher = HTTP_REQUEST_TEMPLATE.matcher(requestUrl);
		while (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

}
