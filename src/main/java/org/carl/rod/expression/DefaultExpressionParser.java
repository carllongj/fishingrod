package org.carl.rod.expression;

/**
 * @author longjie
 * 2021/5/12
 */
public class DefaultExpressionParser implements ExpressionParser {

    private ExpressionParserDelegate expressionParserDelegate;

    public DefaultExpressionParser() {
        this.expressionParserDelegate = new ExpressionParserDelegate();
    }

    public void setExpressionParserDelegate(ExpressionParserDelegate expressionParserDelegate) {
        this.expressionParserDelegate = expressionParserDelegate;
    }

    @Override
    public boolean isSupport(String expression) {
        return expression.startsWith(Expression.EXPRESSION_PREFIX)
                && expression.endsWith(Expression.EXPRESSION_SUFFIX);
    }

    @Override
    public ExpressionComponent parse(String expression) {
        return expressionParserDelegate.parseExpression(expression);
    }
}
