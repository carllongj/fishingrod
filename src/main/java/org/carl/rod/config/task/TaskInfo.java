package org.carl.rod.config.task;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author longjie
 * 2021/5/10
 */
public class TaskInfo {
    /**
     * 请求链接分页处理信息
     */
    private PageInfo pageInfo;

    /**
     * 抓取数据的根路径
     */
    private String baseUrl;

    /**
     * 执行请求,默认为GET请求
     */
    private String method;

    /**
     * 请求头信息
     */
    private Map<String, String> headers;

    /**
     * 设置请求参数信息
     */
    private Map<String, String> parameters;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     * 新增一个请求头
     *
     * @param headerName 请求头名称
     * @param headValue  请求头值
     * @return 返回旧的请求头
     */
    public String addHeader(String headerName, String headValue) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        return headers.put(headerName, headValue);
    }

    /**
     * 新增一个参数配置
     *
     * @param parameterName  参数名
     * @param parameterValue 参数值
     * @return 返回之前的参数值
     */
    public String addParameter(String parameterName, String parameterValue) {
        if (parameters == null) {
            parameters = new LinkedHashMap<>();
        }
        return parameters.put(parameterName, parameterValue);
    }
}
