package org.carl.rod;

import org.carl.rod.utils.StringUtils;
import org.testng.annotations.Test;

/**
 * @author longjie
 * 2021/5/18
 */
public class StringUtilsTest {

	@Test
	public void testHttpRequest() {
		String url = StringUtils.extractHttpBaseUrl("http://www.sc.gov.cn/10462/10464/10797/2021/5/18/ebc8068529c246de8caa68bf450d4d4a.shtml");
		System.out.println(url);
	}
}
