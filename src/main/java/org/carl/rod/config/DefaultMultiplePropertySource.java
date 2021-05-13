package org.carl.rod.config;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author longjie
 * 2021/5/10
 */
public class DefaultMultiplePropertySource implements MultiplePropertySource, PropertySource {

    private Deque<PropertySource> propertySources;

    public DefaultMultiplePropertySource() {
        this.propertySources = new LinkedList<>();
    }

    @Override
    public void addPropertySource(PropertySource propertySource) {
        this.addLast(propertySource);
    }

    @Override
    public void addFirst(PropertySource propertySource) {
        propertySources.addFirst(propertySource);
    }

    @Override
    public void addLast(PropertySource propertySource) {
        propertySources.addLast(propertySource);
    }

    @Override
    public String getProperty(String key) {
        for (PropertySource propertySource : propertySources) {
            if (propertySource.containsProperty(key)) {
                return propertySource.getProperty(key);
            }
        }
        return null;
    }

    @Override
    public void addProperty(String key, String value) {

    }

    @Override
    public boolean containsProperty(String key) {
        for (PropertySource propertySource : propertySources) {
            if (propertySource.containsProperty(key)) {
                return true;
            }
        }
        return false;
    }
}
