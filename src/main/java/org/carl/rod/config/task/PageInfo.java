package org.carl.rod.config.task;

/**
 * @author longjie
 * 2021/5/10
 */
public class PageInfo {

    /**
     * 首页的链接地址
     */
    private String firstPage;

    /**
     * 链接地址的格式
     */
    private String pageFormat;

    /**
     * 分页数据请求路径地址
     * 地址的完整路径由 pageUrl + firstPage -> 第一页路径地址
     * 其它页的完整路径地址由 pageUrl + pageFormat -> 其它页路径地址
     */
    private String pageUrl;

    public String getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
    }

    public String getPageFormat() {
        return pageFormat;
    }

    public void setPageFormat(String pageFormat) {
        this.pageFormat = pageFormat;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
