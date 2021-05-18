package org.carl.rod.core.http.handlers;

import org.carl.rod.config.RodException;

/**
 * @author longjie
 * 2021/5/18
 */
public class NoFinishHandlerFoundException extends RodException {

	public NoFinishHandlerFoundException() {
	}

	public NoFinishHandlerFoundException(String message) {
		super(message);
	}

	public NoFinishHandlerFoundException(String format, Object... args) {
		super(format, args);
	}
}
