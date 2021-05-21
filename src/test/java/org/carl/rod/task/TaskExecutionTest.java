package org.carl.rod.task;

import org.carl.rod.config.YamlConfigurationLoader;
import org.carl.rod.config.base.RodBaseConfiguration;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.advice.DefaultConfigAdvice;
import org.carl.rod.core.task.DefaultTaskFactory;
import org.carl.rod.core.task.HttpTaskFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author longjie
 * 2021/5/18
 */
public class TaskExecutionTest {

	private HttpTaskFactory factory;

	@BeforeTest
	public void init() {
		factory = new DefaultTaskFactory();
		factory.addTaskPostProcessor(new DefaultConfigAdvice());
		factory.setRodBaseConfiguration(YamlConfigurationLoader.load("classpath:test-catch.yml", RodBaseConfiguration.class));
	}

	@Test
	public void testExecutionCatch() {
		List<Task> taskList = factory.createAllTask();
		Assert.assertEquals(taskList.size(), 1);
		for (Task task : taskList) {
			task.executeTask();
		}
	}
}
