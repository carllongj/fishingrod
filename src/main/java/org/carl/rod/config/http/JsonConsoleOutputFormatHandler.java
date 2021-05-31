package org.carl.rod.config.http;

/**
 * @author longjie
 * 2021/5/31
 */
public class JsonConsoleOutputFormatHandler extends AbstractJsonOutputFormatHandler {

	public JsonConsoleOutputFormatHandler() {
		this.setOutputHandler(new ConsoleOutputHandler());
	}
}
