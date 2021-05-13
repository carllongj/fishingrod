package org.carl.rod.expression;

import org.carl.rod.config.PropertySource;

/**
 * 表达式解析上下文,一个表达式会对应一个解析上下文
 *
 * @author longjie
 * 2021/5/12
 */
public interface ExpressionContext<T> {

    /**
     * 执行表达式
     *
     * @param expression     指定的表达式
     * @param propertySource 参数集合(变量值)
     * @return 返回表达式执行结果
     */
    T execute(String expression, PropertySource propertySource);
}
