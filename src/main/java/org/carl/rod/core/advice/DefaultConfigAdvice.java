package org.carl.rod.core.advice;

import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.core.http.DefaultHttpRequestExecutor;
import org.carl.rod.core.http.doc.HttpDocumentCreator;
import org.carl.rod.core.task.HttpTaskFactory;
import org.carl.rod.core.task.TaskCreatePostProcessor;
import org.carl.rod.utils.HttpComponentsUtils;

/**
 * @author longjie
 * 2021/5/18
 */
public class DefaultConfigAdvice implements TaskCreatePostProcessor {

	@Override
	public void beforeCreateTask(HttpTaskFactory taskFactory, TaskConfiguration taskConfiguration) {
		taskFactory.addTaskPostProcessor(new HttpTaskFactoryAdvice(taskFactory));
		// 添加一些默认的行为
		HttpComponentAdvice componentAdvice = new HttpComponentAdvice();
		componentAdvice.setHttpClient(HttpComponentsUtils.createDefault());
		componentAdvice.setRequestExecutor(new DefaultHttpRequestExecutor());
		taskFactory.addTaskPostProcessor(componentAdvice);
		taskFactory.addDocumentCreator(new HttpDocumentCreator());
		taskFactory.addTaskPostProcessor(new HttpMappedValueSupportAdvice());
	}
}
