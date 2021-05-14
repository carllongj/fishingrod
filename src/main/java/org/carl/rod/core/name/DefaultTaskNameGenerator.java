package org.carl.rod.core.name;

import java.util.UUID;

/**
 * @author longjie
 * 2021/5/14
 */
public class DefaultTaskNameGenerator implements TaskNameGenerator {

	@Override
	public String generateTaskName() {
		return UUID.randomUUID().toString();
	}
}
