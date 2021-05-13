package org.carl.rod.config;

/**
 * @author longjie
 * 2021/5/10
 */
public interface PropertySource {

    /**
     * 获取指定名称的配置项
     *
     * @param key 指定的key
     * @return 返回对应的value
     */
    String getProperty(String key);

    /**
     * 新增对应的k-v
     *
     * @param key   指定的key
     * @param value 指定的value
     */
    void addProperty(String key, String value);

    /**
     * 是否包含了指定的名称 key
     *
     * @param key 指定的key
     * @return 返回是否包含了对应的key
     */
    boolean containsProperty(String key);
}
