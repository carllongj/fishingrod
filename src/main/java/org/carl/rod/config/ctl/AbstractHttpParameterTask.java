package org.carl.rod.config.ctl;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.carl.rod.config.base.TaskConfiguration;
import org.carl.rod.config.http.url.GroupedUrlProvider;
import org.carl.rod.config.http.url.UrlGroup;
import org.carl.rod.config.task.HttpRequestTask;
import org.carl.rod.config.task.TaskAware;
import org.carl.rod.core.http.DefaultHttpUriRequestWrapper;
import org.carl.rod.core.http.HttpRequestExecutor;
import org.carl.rod.core.http.HttpResponse;
import org.carl.rod.core.http.HttpUriRequestWrapper;
import org.carl.rod.core.http.doc.HttpResponseComposite;
import org.carl.rod.core.http.doc.NoSuitableDocumentCreatorException;
import org.carl.rod.core.task.TaskAfterHandlePostProcessor;
import org.carl.rod.core.task.TaskFactory;
import org.carl.rod.core.task.TaskPostProcessor;
import org.carl.rod.core.task.TaskPreHandlePostProcessor;
import org.carl.rod.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/13
 */
public abstract class AbstractHttpParameterTask extends AbstractCtlTask implements HttpRequestTask, TaskFactoryAware {

	/**
	 * 日志对象
	 */
	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpParameterTask.class);

	/**
	 * 当前任务的请求方法
	 */
	private String httpMethod;

	/**
	 * 当前任务的请求方法头
	 */
	private volatile Map<String, List<String>> requestHeaders;

	/**
	 * 当前任务的请求参数
	 */
	private volatile Map<String, List<String>> requestParameters;

	/**
	 * 页面抓取的地址链接集合
	 */
	private GroupedUrlProvider urlProvider;

	/**
	 * 当前处理链接的根地址链接
	 */
	private String currentBaseUrl;

	/**
	 * 当前的URL分组
	 */
	private UrlGroup currentGroup;

	/**
	 * 处理的当前数据链接
	 */
	private String currentUrl;

	/**
	 * http请求客户端
	 */
	private CloseableHttpClient httpClient;

	/**
	 * Http 请求执行器
	 */
	private HttpRequestExecutor httpExecutor;

	/**
	 * 当前的任务创建工厂
	 */
	private TaskFactory taskFactory;

	public AbstractHttpParameterTask() {
		this.requestHeaders = new LinkedHashMap<>();
		this.requestParameters = new LinkedHashMap<>();
	}

	public AbstractHttpParameterTask(TaskConfiguration taskConfiguration) {
		super(taskConfiguration);
		this.requestHeaders = new LinkedHashMap<>();
		this.requestParameters = new LinkedHashMap<>();
	}

	@Override
	public Map<String, List<String>> getRequestHeaders() {
		return this.requestHeaders;
	}

	@Override
	public Map<String, List<String>> getRequestParameters() {
		return this.requestParameters;
	}

	@Override
	public void setTaskFactory(TaskFactory taskFactory) {
		this.taskFactory = taskFactory;
	}

	@Override
	public void addRequestHeader(String headerKey, String headValue) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("add new Http Header , key : {}, value:{}", headerKey, headValue);
		}
		List<String> headerValues = this.requestHeaders.get(headerKey);
		if (null == headerValues) {
			headerValues = new ArrayList<>();
		}
		headerValues.add(headValue);
		this.requestHeaders.put(headerKey, headerValues);
	}

	@Override
	public void addRequestParameter(String key, String value) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("add new Http Request Parameter , key : {}, value:{}", key, value);
		}
		List<String> requestParameters = this.requestParameters.get(key);
		if (null == requestParameters) {
			requestParameters = new ArrayList<>();
		}
		requestParameters.add(value);
		this.requestParameters.put(key, requestParameters);
	}

	@Override
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	@Override
	public String getHttpMethod() {
		return this.httpMethod;
	}

	@Override
	public void setHttpComponent(CloseableHttpClient httpComponent) {
		this.httpClient = httpComponent;
	}

	@Override
	public CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	@Override
	public void setHttpExecutor(HttpRequestExecutor executor) {
		this.httpExecutor = executor;
	}

	@Override
	public HttpRequestExecutor getHttpExecutor() {
		return httpExecutor;
	}

	@Override
	public final boolean executeTask() {
		long start = System.currentTimeMillis();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("start execute task {}", this.getTaskName());
		}

		boolean result = doExecuteTask();
		LOGGER.info("task {} finished in {}", getTaskName(), System.currentTimeMillis() - start);
		return result;
	}

	private boolean doExecuteTask() {
		//获取当前任务所有需要执行的请求
		while (urlProvider.hasNext()) {

			// 获取当前的一组url记录
			UrlGroup urlGroup = urlProvider.next();

			// 根据当前的链接地址,创建对应的请求任务列表
			List<HttpUriRequestWrapper> taskRequestList = createTaskRequestList(urlGroup);

			if (Objects.isNull(taskRequestList) || taskRequestList.isEmpty()) {
				LOGGER.warn("Task {} is ignored", this.getTaskName());
				return true;
			}

			// 进行过滤请求
			List<HttpUriRequestWrapper> requestWrappers = filterUrl(taskRequestList);

			for (HttpUriRequestWrapper request : requestWrappers) {

				//记录当前处理的链接相关信息
				this.currentGroup = urlGroup;
				this.currentUrl = request.getOriginUri();
				this.currentBaseUrl = StringUtils.extractHttpBaseUrl(currentUrl);

				if (Objects.nonNull(taskFactory)) {
					List<TaskPostProcessor> processor = this.taskFactory.getTaskPostProcessor();
					for (TaskPostProcessor postProcessor : processor) {
						if (postProcessor instanceof TaskPreHandlePostProcessor) {
							((TaskPreHandlePostProcessor) postProcessor).preHandle(this, request);
						}
					}
				}

				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("start to execute Task : {} ,url : {}", getTaskName(), request.getOriginUri());
				}

				// 执行http请求
				HttpResponse response = this.httpExecutor.
					executeHttpRequest(this.getHttpClient(), request,
						taskFactory.getHttpFinishedHandler());

				if (Objects.isNull(response)) {
					LOGGER.warn("task {} for uri {} without response", this.getTaskName(), request.getOriginUri());
					return false;
				}

				Document doc = this.taskFactory.createDocument(
					createTargetSource(request, response));

				if (null == doc) {
					throw new NoSuitableDocumentCreatorException("no suitable Document Creator for task %s", this.getTaskName());
				}

				// 获取到所有提取到的集合
				Object extractValue = this.getTaskInput().handle(doc);

				// 设置当前的任务信息
				if (extractValue instanceof TaskAware) {
					((TaskAware) extractValue).setTask(this);
				}

				for (TaskOutputHandler taskOutputHandler : this.getTaskOutputHandler()) {
					if (taskOutputHandler.isSupport(extractValue)) {
						try {
							taskOutputHandler.handleOutput(extractValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				if (Objects.nonNull(taskFactory)) {
					List<TaskPostProcessor> processor = this.taskFactory.getTaskPostProcessor();
					for (TaskPostProcessor taskPostProcessor : processor) {
						if (taskPostProcessor instanceof TaskAfterHandlePostProcessor) {
							((TaskAfterHandlePostProcessor) taskPostProcessor).afterHandle(this);
						}
					}
				}
			}
		}

		cleanTask();
		return true;
	}

	@Override
	public void setUrlProvider(GroupedUrlProvider urlProvider) {
		this.urlProvider = urlProvider;
	}

	@Override
	public String getCurrentUrl() {
		return this.currentUrl;
	}

	/**
	 * 创建目标对应的源对象,用以构建对应的文档
	 *
	 * @param request  请求包装内容
	 * @param response 响应内容
	 * @return 返回对应的源对象
	 */
	protected Object createTargetSource(HttpUriRequestWrapper request, HttpResponse response) {
		return new HttpResponseComposite(request, response);
	}

	/**
	 * 过滤需要进行过滤的请求
	 *
	 * @param taskRequestList 所有的任务队列
	 * @return 返回过滤后的请求
	 */
	protected List<HttpUriRequestWrapper> filterUrl(List<HttpUriRequestWrapper> taskRequestList) {

		// 是否需要检查请求过滤
		boolean doCheck = this.enablePathFilter() &&
			Objects.nonNull(this.getUrlPathFilters()) &&
			!this.getUrlPathFilters().isEmpty()
			&& Objects.nonNull(taskRequestList) &&
			!taskRequestList.isEmpty();

		// 若需要执行请求过滤
		if (doCheck) {
			List<HttpUriRequestWrapper> filtered = new ArrayList<>();
			for (int i = 0; i < taskRequestList.size(); i++) {
				// 获取当前的请求包装对象
				HttpUriRequestWrapper wrapper = taskRequestList.get(i);
				for (UrlPathFilter pathFilter : this.getUrlPathFilters()) {
					//所有的过滤器遍历处理
					if (!pathFilter.filter(wrapper.getOriginUri())) {
						filtered.add(wrapper);
					}
				}
			}
			return filtered;
		}
		return taskRequestList;
	}

	/**
	 * 尝试获取子类提供的url
	 *
	 * @return 返回子类提供的基础路径
	 */
	protected String getProvidedUri() {
		return this.currentUrl;
	}

	/**
	 * 创建默认的任务请求数据队列
	 *
	 * @param urlGroup  url分组
	 * @return 返回所有的任务列表
	 */
	protected List<HttpUriRequestWrapper> createTaskRequestList(UrlGroup urlGroup) {
		List<HttpUriRequestWrapper> requestUrls = new ArrayList<>();
		// 构建当前分组的请求对象
		for (String url : urlGroup.getGroupUrl()) {
			requestUrls.add(new DefaultHttpUriRequestWrapper(
				url, buildHttpRequest(url)));
		}
		return requestUrls;
	}

	/**
	 * 执行构建对应的Http请求
	 *
	 * @param url 构建对应的url的请求
	 * @return 返回对应的Http请求方法
	 */
	protected HttpUriRequest buildHttpRequest(String url) {
		RequestBuilder builder = RequestBuilder.create(httpMethod.toUpperCase())
			.setUri(url)
			.setCharset(StandardCharsets.UTF_8);
		Map<String, List<String>> requestHeaders = this.getRequestHeaders();
		if (Objects.nonNull(requestHeaders) && !requestHeaders.isEmpty()) {
			for (Map.Entry<String, List<String>> entry : requestHeaders.entrySet()) {
				for (String value : entry.getValue()) {
					Header header = new BasicHeader(entry.getKey(), value);
					builder.addHeader(header);
				}
			}
		}

		Map<String, List<String>> requestParameters = this.getRequestParameters();
		if (Objects.nonNull(requestParameters) && !requestParameters.isEmpty()) {
			for (Map.Entry<String, List<String>> entry : requestParameters.entrySet()) {
				for (String value : entry.getValue()) {
					builder.addParameter(entry.getKey(), value);
				}
			}
		}

		//勾子
		this.prePostRequestBuilder(builder);
		return builder.build();
	}

	/**
	 * 清空当前Task的缓存
	 */
	protected void cleanTask() {
		this.currentBaseUrl = null;
		this.currentUrl = null;
		this.currentGroup = null;
	}

	/**
	 * 后续构建时可以定制该RequestBuilder
	 *
	 * @param requestBuilder 请求构造器
	 */
	protected void prePostRequestBuilder(RequestBuilder requestBuilder) {
	}
}
