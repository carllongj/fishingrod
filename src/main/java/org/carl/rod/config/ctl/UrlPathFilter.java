package org.carl.rod.config.ctl;

/**
 * @author longjie
 * 2021/5/13
 */
public interface UrlPathFilter {

    /**
     * 当前当前的请求路径地址是否需要被忽略
     *
     * @param url 指定的url
     * @return 返回该路径是否被忽略 <code>true</code>表示忽略该请求,<code>false</code>表示忽略该请求
     */
    boolean filter(String url);
}
