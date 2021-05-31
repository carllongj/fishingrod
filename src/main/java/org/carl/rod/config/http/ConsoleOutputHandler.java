package org.carl.rod.config.http;

import org.carl.rod.config.base.OutputConfiguration;
import org.carl.rod.config.task.HttpRequestTask;

import java.io.IOException;

/**
 * @author longjie
 * 2021/5/31
 */
public class ConsoleOutputHandler implements OutputHandler {

	@Override
	public void doOutput(HttpRequestTask requestTask, String formatLine, OutputConfiguration outputConfiguration) throws IOException {
		System.out.println(formatLine);
	}
}
