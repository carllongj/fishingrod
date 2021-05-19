package org.carl.rod.config.http;

import java.util.Map;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/19
 */
public class JsonFormatOutputHandler extends AbstractFileOutputFormatHandler {

	/**
	 * 开始标记
	 */
	private static final char START_FLAG = '{';

	/**
	 * 结束标记
	 */
	private static final char END_FLAG = '}';

	/**
	 * 对象的构建标识
	 */
	private static final char OBJECT_TAG = '"';

	/**
	 * KEY VALUE的分隔符
	 */
	private static final char OBJECT_SEPARATOR = ':';

	/**
	 * 字段分隔符
	 */
	private static final char FIELD_SEPARATOR = ',';

	/**
	 * 行结束标识
	 */
	private static final char LINE_END_FLAG = '\n';

	/**
	 * 默认的字节数据长度
	 */
	private static final int DEFAULT_BUFFER_LENGTH = 4096;


	@Override
	protected String formatLine(HttpMappedValue result) {
		Map<String, String> extractMap = result.getExtractMap();
		if (Objects.isNull(extractMap) || extractMap.isEmpty()) {
			return null;
		}

		StringBuilder sb = new StringBuilder(DEFAULT_BUFFER_LENGTH);
		sb.append(START_FLAG);
		for (Map.Entry<String, String> entry : extractMap.entrySet()) {
			sb.append(OBJECT_TAG).append(entry.getKey()).append(OBJECT_TAG)
				.append(OBJECT_SEPARATOR)
				.append(OBJECT_TAG).append(entry.getValue()).append(OBJECT_TAG)
				.append(FIELD_SEPARATOR);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.append(END_FLAG).append(LINE_END_FLAG).toString();
	}
}
