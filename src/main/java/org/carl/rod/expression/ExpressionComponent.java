package org.carl.rod.expression;

import java.util.Set;

/**
 * @author longjie
 * 2021/5/12
 */
public interface ExpressionComponent {

    /**
     * 获取原始表达式
     *
     * @return 返回对应的原始表达式
     */
    String getExpression();

    /**
     * 获取表达式设置的变量
     *
     * @return 返回对应的变量名称集合
     */
    Set<String> getVariables();

}
