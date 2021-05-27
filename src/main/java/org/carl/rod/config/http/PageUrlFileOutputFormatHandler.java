package org.carl.rod.config.http;

import org.carl.rod.utils.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/21
 */
public class PageUrlFileOutputFormatHandler extends AbstractFileOutputFormatHandler {

	@Override
	protected String formatLine(HttpMappedValue result) {
		Map<String, List<String>> extractMap = result.getExtractMap();
		if (Objects.isNull(extractMap) || extractMap.isEmpty()) {
			return StringUtils.EMPTY;
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, List<String>> entry : extractMap.entrySet()) {
			for (String entryValue : entry.getValue()) {
				sb.append(entryValue).append(StringUtils.LINE_SEPARATOR);
			}
		}
		return sb.toString();
	}
}
