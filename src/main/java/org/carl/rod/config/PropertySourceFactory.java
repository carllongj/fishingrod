package org.carl.rod.config;

/**
 * @author longjie
 * 2021/5/10
 */
public interface PropertySourceFactory {

    /**
     * 当前是否支持指定后缀配置文件解析
     *
     * @param suffix 文件后缀
     * @return 返回是否支持
     */
    boolean isSupport(String suffix);

    /**
     * 获取指定的配置
     *
     * @param location 指定的路径地址
     * @return 返回加载后的路径地址
     */
    PropertySource getPropertySource(String location);
}
