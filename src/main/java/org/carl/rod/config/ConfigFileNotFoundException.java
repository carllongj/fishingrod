package org.carl.rod.config;

/**
 * @author longjie
 * 2021/5/13
 */
public class ConfigFileNotFoundException extends RodException {

    public ConfigFileNotFoundException() {
    }

    public ConfigFileNotFoundException(String message) {
        super(message);
    }

    public ConfigFileNotFoundException(String format, Object... args) {
        super(format, args);
    }
}
