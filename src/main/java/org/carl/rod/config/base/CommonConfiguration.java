package org.carl.rod.config.base;

/**
 * @author longjie
 * 2021/5/19
 */
public class CommonConfiguration {

	/**
	 * 默认的输出配置
	 */
	private OutputConfiguration output;

	/**
	 * 默认的一些Http请求参数
	 */
	private HttpRequestConfiguration http;

	public OutputConfiguration getOutput() {
		return output;
	}

	public void setOutput(OutputConfiguration output) {
		this.output = output;
	}

	public HttpRequestConfiguration getHttp() {
		return http;
	}

	public void setHttp(HttpRequestConfiguration http) {
		this.http = http;
	}
}
