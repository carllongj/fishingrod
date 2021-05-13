package org.carl.rod.expression;

import org.carl.rod.config.RodException;

/**
 * @author longjie
 * 2021/5/12
 */
public class ExpressionException extends RodException {

    public ExpressionException() {
        super();
    }

    public ExpressionException(String message) {
        super(message);
    }

    public ExpressionException(String format, Object... args) {
        super(format, args);
    }
}
