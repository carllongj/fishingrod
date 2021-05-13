package org.carl.rod.expression;

import org.carl.rod.config.PropertySource;

/**
 * @author longjie
 * 2021/5/12
 */
public interface ExpressionEngine<T> {

    /**
     * 执行对应的表达式
     *
     * @param component      指定的表达式组件
     * @param propertySource 参数信息
     * @return 返回执行结果
     */
    T execute(ExpressionComponent component, PropertySource propertySource);
}
