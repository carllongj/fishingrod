package org.carl.rod.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author longjie
 * 2021/5/19
 */
public abstract class CloseUtils {

	/**
	 * 关闭所有的可关闭流
	 *
	 * @param closeable 可关闭流数组
	 */
	public static void closeQuietly(Closeable... closeable) {
		if (null != closeable && closeable.length > 0) {
			for (Closeable close : closeable) {
				try {
					close.close();
				} catch (IOException e) {
					//quiet close
				}
			}
		}
	}
}
