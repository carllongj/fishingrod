package org.carl.rod.config.http.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取指定行数的缓冲流
 *
 * @author longjie
 * 2021/5/19
 */
class SpecificLinesReader extends BufferedReader {

	private BufferedReader bufferedReader;

	public SpecificLinesReader(BufferedReader in) {
		super(in);
	}

	public List<String> readSpecificLines(int specificLines) throws IOException {
		List<String> list = new ArrayList<>(specificLines);
		int count = 0;
		while (count++ < specificLines) {
			String line = bufferedReader.readLine();
			// 已经读取到文件末尾,直接返回对应的
			if (null == line) {
				return list;
			}
			list.add(line);
		}
		return list;
	}
}
