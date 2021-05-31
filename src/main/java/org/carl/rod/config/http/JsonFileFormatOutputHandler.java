package org.carl.rod.config.http;

/**
 * @author longjie
 * 2021/5/19
 */
public class JsonFileFormatOutputHandler extends AbstractJsonOutputFormatHandler {

	public JsonFileFormatOutputHandler() {
		this.setOutputHandler(new FileOutputHandler());
	}
}
