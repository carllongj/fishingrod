package org.carl.rod.task;

import org.carl.rod.config.YamlConfigurationLoader;
import org.carl.rod.config.base.RodBaseConfiguration;
import org.carl.rod.config.task.Task;
import org.carl.rod.core.task.DefaultTaskFactory;
import org.carl.rod.core.task.TaskFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author longjie
 * 2021/5/14
 */
public class DefaultTaskFactoryTest {

	private TaskFactory taskFactory;

	@BeforeTest
	public void init() {
		taskFactory = new DefaultTaskFactory();
	}

	@Test
	public void testCreateTask() {
		RodBaseConfiguration configuration = YamlConfigurationLoader.load("classpath:/fishingrod-template.yml", RodBaseConfiguration.class);
		List<Task> task = taskFactory.createTask(configuration);
		Assert.assertEquals(1, task.size());
	}
}
