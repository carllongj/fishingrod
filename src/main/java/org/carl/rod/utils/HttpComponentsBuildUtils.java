package org.carl.rod.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * @author longjie
 * 2021/5/14
 */
public abstract class HttpComponentsBuildUtils {

	/**
	 * 创建默认的 Http请求客户端
	 *
	 * @return 返回执行 http请求客户端
	 */
	public static CloseableHttpClient createDefault() {
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory())
			.register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		connectionManager.setMaxTotal(200);
		connectionManager.setDefaultMaxPerRoute(100);
		connectionManager.setValidateAfterInactivity(2000);

		RequestConfig requestConfig = RequestConfig.custom()
			// 设置响应超时时间
			.setSocketTimeout(65000)
			// 设置连接超时时间
			.setConnectTimeout(5000)
			//设置连接请求超时时间
			.setConnectionRequestTimeout(1000)
			.build();
		// 创建Http客户端
		return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
			.setConnectionManager(connectionManager).build();
	}
}