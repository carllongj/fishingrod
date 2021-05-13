package org.carl.rod.config.page;

/**
 * 用于分页抓取数据
 *
 * @author longjie
 * 2021/5/10
 */
public interface PageStrategy {

    /**
     * 获取指定的分页url地址
     *
     * @param page 指定的页码数
     * @return 返回对应的页的地址
     */
    String getPageUrl(Integer page);
}
