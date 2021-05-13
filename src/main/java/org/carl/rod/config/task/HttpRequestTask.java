package org.carl.rod.config.task;

import java.util.List;
import java.util.Map;

/**
 * @author longjie
 * 2021/5/13
 */
public interface HttpRequestTask extends Task {

    /**
     * 获取当前任务参数的请求头
     *
     * @return 返回任务参数请求头
     */
    Map<String, List<String>> getRequestHeaders();

    /**
     * 新增请求头参数
     *
     * @param headerKey 请求头key
     * @param headValue 请求头value
     */
    void addRequestHeader(String headerKey, String headValue);

    /**
     * 获取当前的请求参数
     *
     * @return 返回当前的请求参数
     */
    Map<String, List<String>> getRequestParameters();

    /**
     * 新增请求参数
     *
     * @param key   指定的请求key
     * @param value 请求value
     */
    void addRequestParameter(String key, String value);

    /**
     * 设置当前请求方式
     *
     * @param httpMethod 指定的请求方式
     */
    void setHttpMethod(String httpMethod);

    /**
     * 获取当前的请求方式
     *
     * @return 返回当前请求方式
     */
    String getHttpMethod();

    /**
     * 指定当前任务的根链接地址
     *
     * @param baseUrl 基础请求链接地址
     */
    void setBaseUrl(String baseUrl);

    /**
     * 获取当前设置的根路径
     *
     * @return 返回对应的根路径地址
     */
    String getBaseUrl();
}
