package org.carl.rod.expression;

/**
 * @author longjie
 * 2021/5/12
 */
public abstract class AbstractExpressionComponent implements ExpressionComponent {

    private final String expression;

    public AbstractExpressionComponent(String expression) {
        this.expression = expression;
    }

    @Override
    public String getExpression() {
        return this.expression;
    }
}
