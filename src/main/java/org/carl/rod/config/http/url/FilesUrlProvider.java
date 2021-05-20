package org.carl.rod.config.http.url;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/19
 */
public class FilesUrlProvider extends AbstractGroupedUrlProvider {

	/**
	 * 记录当前的文件列表
	 */
	private List<String> files;

	/**
	 *
	 */
	private List<String> urls = Collections.emptyList();

	/**
	 * 文件流式读取
	 */
	private StreamLinesReader linesReader;

	public FilesUrlProvider() {
	}

	public FilesUrlProvider(List<String> files) {
		this.files = files;
		this.linesReader = new StreamLinesReader(files);
	}

	public void setFile(List<String> files) {
		this.files = files;
		this.linesReader = new StreamLinesReader(files);
	}

	@Override
	protected List<String> urlsGroup() {
		return this.urls;
	}

	@Override
	public boolean hasNext() {
		if (null == urls) {
			return false;
		}
		// 尝试读取指定分组大小的数据列表
		List<String> lines = this.linesReader.readLines(getGroupSize());
		if (Objects.isNull(lines)) {
			return false;
		}
		this.urls = lines;
		return true;
	}
}
