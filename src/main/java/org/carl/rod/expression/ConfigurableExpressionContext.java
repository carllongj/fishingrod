package org.carl.rod.expression;

/**
 * @author longjie
 * 2021/5/12
 */
public interface ConfigurableExpressionContext<T> extends ExpressionContext<T> {

    /**
     * 新增对应的解析器
     *
     * @param expressionParser 表达式解析器
     */
    void addExpressionParser(ExpressionParser expressionParser);
}
