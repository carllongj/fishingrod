package org.carl.rod.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.carl.rod.config.http.HttpHeaderKey;
import org.carl.rod.core.http.DefaultHttpResponse;
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

	@BeforeTest
	public void init() {
		this.httpClient = HttpComponentsUtils.createDefault();
	}

	@Test
	public void testExecuteHttpRequest() throws IOException {
		CloseableHttpResponse response = httpClient.execute(new HttpGet("https://carllongj.github.io"));
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getProtocolVersion().toString(), "HTTP/1.1");

		response = httpClient.execute(new HttpGet("https://carllongj.github.io/carllong.html"));
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
		Assert.assertEquals(response.getHeaders(HttpHeaderKey.CONTENT_TYPE).length, 1);
	}

	@Test
	public void testHttpResponse() throws IOException {
		CloseableHttpResponse response = httpClient.execute(new HttpGet("https://carllongj.github.io"));
		DefaultHttpResponse httpResponse = new DefaultHttpResponse(response);
		Assert.assertEquals(httpResponse.getResponseCode(), 200);
		Assert.assertEquals(httpResponse.getHeaderValue(HttpHeaderKey.CONTENT_TYPE), "text/html; charset=utf-8");
		Assert.assertNotNull(httpResponse.getResponseContent());
	}
}
