package org.carl.rod.config.http;

import org.carl.rod.config.RodException;

/**
 * @author longjie
 * 2021/5/18
 */
public class UnsupportedContentTypeException extends RodException {

	public UnsupportedContentTypeException() {
	}

	public UnsupportedContentTypeException(String message) {
		super(message);
	}

	public UnsupportedContentTypeException(String format, Object... args) {
		super(format, args);
	}
}
