package org.carl.rod.config.http.url;

import java.util.ArrayList;
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
	 * 记录当前索引到的文件索引
	 */
	private int index;

	/**
	 * 当前的文件集合缓存
	 */
	private List<String> buffer = new ArrayList<>();

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
		List<String> urlList = Collections.unmodifiableList(buffer);
		buffer.clear();
		return urlList;
	}

	@Override
	public boolean hasNext() {
		if (null == buffer) {
			return false;
		} else {
			List<String> lines = linesReader.readLines(getGroupSize());
			if (Objects.isNull(lines)) {
				return false;
			} else {
				this.buffer.addAll(lines);
				return true;
			}
		}
	}
}
