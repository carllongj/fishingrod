package org.carl.rod.config;

import org.carl.rod.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/13
 */
public class YamlConfigurationLoader {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(YamlConfigurationLoader.class);


    public static <T> T load(String location, Class<T> clazz) {

        if (null == location || location.isEmpty()) {
            return null;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("load ymal config location {}", location);
        }
        try {
            if (location.startsWith("classpath:")) {
                return load(ClassLoader.getSystemResourceAsStream(location.substring(10)), clazz);
            } else {
                return load(new FileInputStream(location), clazz);
            }
        } catch (FileNotFoundException e) {
            throw new ConfigFileNotFoundException("file location %s is not exists", location);
        }
    }

    public static <T> T load(InputStream inputStream, Class<T> clazz) {
        if (Objects.isNull(inputStream)) {
            return null;
        }

        Constructor constructor = new Constructor();
        constructor.setPropertyUtils(new FieldMappingPropertyUtils());
        Yaml yaml = new Yaml(constructor);
        return yaml.loadAs(inputStream, clazz);
    }

    /**
     * 用以实现Property字段映射
     */
    private static class FieldMappingPropertyUtils extends PropertyUtils {


        @Override
        protected Map<String, Property> getPropertiesMap(Class<?> type, BeanAccess bAccess) {
            Map<String, Property> propertyMap = super.getPropertiesMap(type, bAccess);
            // 添加中划线命名
            addStrikeThroughName(propertyMap);
            return propertyMap;
        }

        /**
         * 修改驼峰命名
         *
         * @param propertyMap 指定的propertyMap
         */
        public void addStrikeThroughName(Map<String, Property> propertyMap) {

            if (null == propertyMap || propertyMap.isEmpty()) {
                return;
            }

            Map<String, Property> targetMap = new HashMap<>();

            for (Map.Entry<String, Property> propertyEntry : propertyMap.entrySet()) {
                String keyName = StringUtils.replaceHumpStyleToStrikeThrough(propertyEntry.getKey());
                if (keyName.equals(propertyEntry.getKey())) {
                    continue;
                }
                targetMap.put(keyName, propertyEntry.getValue());
            }

            propertyMap.putAll(targetMap);
        }
    }
}
