package org.carl.rod.config;

/**
 * @author longjie
 * 2021/5/10
 */
public interface MultiplePropertySource {

    /**
     * 新增一个propertySource 配置项
     *
     * @param propertySource 指定的propertySource
     */
    void addPropertySource(PropertySource propertySource);

    /**
     * 将对应的配置项添加到最前
     *
     * @param propertySource 指定的配置项
     */
    void addFirst(PropertySource propertySource);

    /**
     * 将对应的配置项添加到最后
     *
     * @param propertySource 指定的配置项
     */
    void addLast(PropertySource propertySource);
}
