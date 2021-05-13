package org.carl.rod.expression;

/**
 * @author longjie
 * 2021/5/12
 */
public class NoSuitableExpressionParserFoundException extends ExpressionException {

    public NoSuitableExpressionParserFoundException() {
    }

    public NoSuitableExpressionParserFoundException(String message) {
        super(message);
    }

    public NoSuitableExpressionParserFoundException(String format, Object... args) {
        super(format, args);
    }
}
