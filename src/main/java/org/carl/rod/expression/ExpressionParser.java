package org.carl.rod.expression;

/**
 * @author longjie
 * 2021/5/12
 */
public interface ExpressionParser {

    /**
     * 是否支持当前的表达式解析器
     *
     * @param expression 指定的表达式
     * @return 返回是否支持该表达式
     */
    boolean isSupport(String expression);

    /**
     * 指定的表达式解析器
     *
     * @param expression 解析对应的表达式
     * @return 返回解析后的组件
     */
    ExpressionComponent parse(String expression);
}
