package org.carl.rod.config.http.url;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/19
 */
public class StreamLinesReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(StreamLinesReader.class);

	/**
	 * 记录当前的所有文件列表
	 */
	private List<String> pathList;

	/**
	 * 指定的文件读取索引标识
	 */
	private int index;

	/**
	 * 设置字符集编码
	 */
	private Charset charset;

	/**
	 * 当前文件读取的缓存
	 */
	private SpecificLinesReader specificLinesReader;

	/**
	 * 缓存当前的数据记录
	 */
	private List<String> buffer = new ArrayList<>();

	/**
	 * 创建默认的文件列表集合
	 *
	 * @param pathList 文件列表
	 */
	public StreamLinesReader(List<String> pathList) {
		this.pathList = pathList;
		this.charset = StandardCharsets.UTF_8;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	/**
	 * 读取的指定行数的内容
	 *
	 * @param specificLines 设置读取的行数
	 * @return 返回读取到的结果, 若为null表示所有数据已经读取完毕
	 */
	public List<String> readLines(int specificLines) {

		// 是否还可以读取数据
		boolean canRead = Objects.nonNull(specificLinesReader) || index < pathList.size();

		//数据流已经完全被读取完毕,且无剩下文件内容读取,返回null表示已经没有数据读取了
		if (!canRead && buffer.isEmpty()) {
			return null;
		}

		// 数据读取完毕,但是缓冲区中还存在内容
		if (!canRead) {
			return Collections.unmodifiableList(buffer);
		}

		// 还需要读取的记录数
		int needReadLines = specificLines;

		// 若当前的缓冲区不为空,只需要读取一部分数据
		if (!buffer.isEmpty()) {
			needReadLines = specificLines - buffer.size();
		}


		// 当前文件数据读取完毕
		if (Objects.isNull(specificLinesReader)) {
			File targetFile = null;
			while (index < pathList.size()) {
				Path path = Paths.get(pathList.get(index));
				if (!path.toFile().exists()) {
					LOGGER.warn("path {} not exists", path.toString());
					index++;
				} else {
					targetFile = path.toFile();
				}
			}
			// 没有文件了
			if (Objects.isNull(targetFile)) {
				return null;
			}
			Reader reader;
			try {
				reader = new InputStreamReader(new FileInputStream(targetFile), this.charset);
			} catch (FileNotFoundException e) {
				// if happened,ignore file
				//checked
				index++;
				specificLinesReader = null;
				return readLines(needReadLines);
			}

			specificLinesReader = new SpecificLinesReader(new BufferedReader(reader));
		}

		// 准备执行返回数据
		List<String> currentLines = null;
		try {
			currentLines = specificLinesReader.readSpecificLines(needReadLines);
		} catch (IOException e) {
			index++;
			specificLinesReader = null;
			return readLines(needReadLines);
		}

		// 若仍然小于对应的读取记录数
		if (currentLines.size() < specificLines) {
			index++;
			specificLinesReader = null;
			buffer.addAll(currentLines);
			return readLines(specificLines - currentLines.size());
		}

		// 数据已经足够,将其加入到缓存中
		buffer.addAll(currentLines);

		// 构建集合不可变
		List<String> urlList = Collections.unmodifiableList(buffer);

		//清空缓存
		buffer.clear();
		return urlList;
	}
}
