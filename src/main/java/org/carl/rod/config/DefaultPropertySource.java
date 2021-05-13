package org.carl.rod.config;

import java.util.Collections;
import java.util.Map;

/**
 * @author longjie
 * 2021/5/10
 */
public class DefaultPropertySource implements PropertySource {

    private final Map<String, String> properties;

    public DefaultPropertySource() {
        this(Collections.emptyMap());
    }

    public DefaultPropertySource(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public boolean containsProperty(String key) {
        return false;
    }
}
