package org.carl.rod.config;

/**
 * @author longjie
 * 2021/5/12
 */
public class RodException extends RuntimeException {
    public RodException() {
        super();
    }

    public RodException(String message) {
        super(message);
    }

    public RodException(String format, Object... args) {
        this(String.format(format, args));
    }
}
