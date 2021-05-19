package org.carl.rod.config.http.url;

import org.carl.rod.config.RodException;
import org.carl.rod.config.base.RodBaseConfiguration;

/**
 * @author longjie
 * 2021/5/19
 */
public class UrlConfigFileNotFoundException extends RodException {

	public UrlConfigFileNotFoundException() {
	}

	public UrlConfigFileNotFoundException(String message) {
		super(message);
	}

	public UrlConfigFileNotFoundException(String format, Object... args) {
		super(format, args);
	}
}
