package org.carl.rod.core.http.handlers;

import org.carl.rod.config.RodException;

/**
 * @author longjie
 * 2021/5/18
 */
public class NoSuitableHandlerException extends RodException {

	public NoSuitableHandlerException() {
	}

	public NoSuitableHandlerException(String message) {
		super(message);
	}

	public NoSuitableHandlerException(String format, Object... args) {
		super(format, args);
	}
}
