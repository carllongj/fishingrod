package org.carl.rod.task;

import org.carl.rod.RodMain;
import org.testng.annotations.Test;

/**
 * @author longjie
 * 2021/5/31
 */
public class RodMainTest {

	@Test
	public void testMain() {
		RodMain.main(new String[]{"classpath:sample.yml"});
	}
}
