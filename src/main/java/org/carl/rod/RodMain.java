package org.carl.rod;

import org.carl.rod.config.YamlConfigurationLoader;
import org.carl.rod.config.base.RodBaseConfiguration;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.advice.DefaultConfigAdvice;
import org.carl.rod.core.task.DefaultTaskFactory;

import java.util.List;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/13
 */
public class RodMain {
	public static void main(String[] args) {
		checkArguments(args);
		DefaultTaskFactory factory = new DefaultTaskFactory();
		factory.addTaskPostProcessor(new DefaultConfigAdvice());
		for (String location : args) {
			RodBaseConfiguration configuration = YamlConfigurationLoader.load(location, RodBaseConfiguration.class);
			factory.setRodBaseConfiguration(configuration);
			List<Task> taskList = factory.createAllTask();
			for (Task task : taskList) {
				task.executeTask();
			}
		}
	}

	private static void checkArguments(String[] args) {
		if (Objects.isNull(args) || args.length == 0) {
			printMsg("config file path can not be null");
			printMsg("Usage: java -jar FishingRod configFile...");
		}
	}

	private static void printMsg(String message) {
		System.err.println(message);
	}
}
