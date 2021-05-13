package org.carl.rod.config.base;

import org.carl.rod.config.task.PageInfo;

/**
 * @author longjie
 * 2021/5/13
 */
public class TaskConfiguration {

    /**
     * 进行分页查询任务设置
     */
    private PageInfo pageConfig;

    /**
     * http 基础根路径设置
     */
    private String baseUrl;

    /**
     * http 请求url设置
     */
    private String url;

    /**
     * http请求方法设置
     */
    private String httpMethod;

    /**
     * http 请求参数和请求头设置
     */
    private HttpRequestConfiguration httpConfig;

    public PageInfo getPageConfig() {
        return pageConfig;
    }

    public void setPageConfig(PageInfo pageConfig) {
        this.pageConfig = pageConfig;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public HttpRequestConfiguration getHttpConfig() {
        return httpConfig;
    }

    public void setHttpConfig(HttpRequestConfiguration httpConfig) {
        this.httpConfig = httpConfig;
    }
}
