package org.carl.rod.core.http.doc;

import org.carl.rod.config.RodException;

/**
 * @author longjie
 * 2021/5/18
 */
public class NoSuitableDocumentCreatorException extends RodException {

	public NoSuitableDocumentCreatorException() {
	}

	public NoSuitableDocumentCreatorException(String message) {
		super(message);
	}

	public NoSuitableDocumentCreatorException(String format, Object... args) {
		super(format, args);
	}
}
