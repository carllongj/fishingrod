/*
 * Copyright 2021 carllongj
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package org.carl.rod.config.url;

import org.carl.rod.config.http.url.StreamLinesReader;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author carllongj
 * 2021/5/20 11:40
 */
public class StreamLinesReaderTest {


	private List<String> files;

	@BeforeTest
	public void testGenerateFilesList() {
		File file = new File("D:\\work\\learn\\idea\\product\\fishing-rod\\src\\test\\resources\\tempfile");
		File[] listFiles = file.listFiles();
		List<String> list = new ArrayList<>();
		for (File targetFile : listFiles) {
			list.add(targetFile.toString());
		}

		file = new File("D:\\work\\learn\\idea\\product\\fishing-rod\\src\\main\\java\\org\\carl\\rod\\core\\http");
		listFiles = file.listFiles();
		for (File targetFile : listFiles) {
			if (targetFile.isFile()) {
				list.add(targetFile.toString());
			}
		}


		files = list;
	}

	@Test
	public void testReadSpecificLines() {
		StreamLinesReader reader = new StreamLinesReader(files);
		List<String> lines;
		while (null != (lines = reader.readLines(78))) {
			System.out.println(lines.size());
		}
	}
}
