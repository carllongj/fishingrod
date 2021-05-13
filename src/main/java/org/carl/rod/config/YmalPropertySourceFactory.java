package org.carl.rod.config;

import org.carl.rod.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import java.util.Map;
import java.util.Objects;

/**
 * @author longjie
 * 2021/5/10
 */
public class YmalPropertySourceFactory implements PropertySourceFactory {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(YmalPropertySourceFactory.class);

    /**
     * 支持解析的文件后缀名称
     */
    private static final String[] SUFFIX_ARRAY = new String[]{"yml", "yaml"};

    @Override
    public boolean isSupport(String suffix) {
        for (int i = 0; i < SUFFIX_ARRAY.length; i++) {
            if (StringUtils.equalsIgnoreCase(SUFFIX_ARRAY[i], suffix)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public PropertySource getPropertySource(String location) {
        if (Objects.isNull(location) || location.isEmpty()) {
            return null;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("load ymal config location {}", location);
        }
        return null;
    }
}
