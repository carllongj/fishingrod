package org.carl.rod.core.http.doc;

import org.carl.rod.config.ctl.Document;
import org.carl.rod.config.http.DefaultHttpDocument;

/**
 * @author longjie
 * 2021/5/18
 */
public class HttpDocumentCreator implements DocumentCreator {

	@Override
	public boolean isSupport(Object value) {
		return value instanceof HttpResponseComposite;
	}

	@Override
	public Document createDocument(Object value) {
		return new DefaultHttpDocument((HttpResponseComposite) value);
	}
}
