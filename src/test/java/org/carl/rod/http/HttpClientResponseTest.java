package org.carl.rod.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.carl.rod.config.http.HttpKeys;
import org.carl.rod.core.http.DefaultHttpRequestExecutor;
import org.carl.rod.core.http.DefaultHttpResponse;
import org.carl.rod.core.http.DefaultHttpUriRequestWrapper;
import org.carl.rod.core.http.HttpRequestExecutor;
import org.carl.rod.core.http.HttpUriRequestWrapper;
import org.carl.rod.utils.HttpComponentsUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author longjie
 * 2021/5/17
 */
public class HttpClientResponseTest {

	private CloseableHttpClient httpClient;

	private HttpRequestExecutor requestExecutor;

	@BeforeTest
	public void init() {
		this.httpClient = HttpComponentsUtils.createDefault();
		this.requestExecutor = new DefaultHttpRequestExecutor();
	}

	@Test
	public void testExecuteHttpRequest() throws IOException {
		HttpGet get = new HttpGet("https://carllongj.github.io");
		CloseableHttpResponse response = httpClient.execute(get);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getProtocolVersion().toString(), "HTTP/1.1");

		response = httpClient.execute(new HttpGet("https://carllongj.github.io/carllong.html"));
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
		Assert.assertEquals(response.getHeaders(HttpKeys.CONTENT_TYPE).length, 1);
	}

	@Test
	public void testHttpResponse() throws IOException {
		String uri = "https://carllongj.github.io";
		HttpUriRequestWrapper requestWrapper = new DefaultHttpUriRequestWrapper(uri, new HttpGet(uri));
		CloseableHttpResponse response = httpClient.execute(requestWrapper.getHttpUriRequest());
		DefaultHttpResponse httpResponse = new DefaultHttpResponse(response, requestWrapper);
		Assert.assertEquals(httpResponse.getResponseCode(), 200);
		Assert.assertEquals(httpResponse.getHeaderValue(HttpKeys.CONTENT_TYPE), "text/html; charset=utf-8");
		Assert.assertNotNull(httpResponse.getResponseContent());
	}

	@Test
	public void executeHttpRequest() {
		HttpUriRequest build = RequestBuilder.create("GET").setUri("https://carllongj.github.io").build();
		System.out.println(build);
	}
}
