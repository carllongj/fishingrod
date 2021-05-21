package org.carl.rod.task;

import org.carl.rod.config.YamlConfigurationLoader;
import org.carl.rod.config.base.RodBaseConfiguration;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.advice.HttpTaskFactoryAdvice;
import org.carl.rod.core.name.DefaultTaskNameGenerator;
import org.carl.rod.core.task.DefaultTaskFactory;
import org.carl.rod.core.task.HttpTaskFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author longjie
 * 2021/5/14
 */

class DefaultTaskFactoryTest {

	private HttpTaskFactory taskFactory;

	@BeforeTest
	public void init() {
		taskFactory = new DefaultTaskFactory();
		taskFactory.setTaskNameGenerator(new DefaultTaskNameGenerator());
		taskFactory.addTaskPostProcessor(new HttpTaskFactoryAdvice(taskFactory));
		RodBaseConfiguration configuration = YamlConfigurationLoader.load("classpath:fishingrod-template.yml", RodBaseConfiguration.class);
		taskFactory.setRodBaseConfiguration(configuration);
	}

	@Test
	public void testCreateTask() {
		List<Task> task = taskFactory.createAllTask();
		Assert.assertEquals(1, task.size());
	}

	@Test
	public void builderTaskRequest() {
		List<Task> taskList = taskFactory.createAllTask();
		for (Task task : taskList) {
			boolean executeTask = task.executeTask();
		}
	}
}
