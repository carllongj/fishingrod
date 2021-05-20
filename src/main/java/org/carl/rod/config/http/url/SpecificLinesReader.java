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

	private final BufferedReader bufferedReader;

	public SpecificLinesReader(BufferedReader in) {
		super(in);
		this.bufferedReader = in;
	}

	/**
	 * 读取指定行数的数据,若在该次读取中已经读取完毕且不足 <param>specificLines</param>
	 * @param specificLines 指定的行数
	 * @return 返回读取到的数据
	 * @throws IOException 出现IO异常
	 */
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
