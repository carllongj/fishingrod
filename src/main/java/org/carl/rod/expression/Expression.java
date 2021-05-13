package org.carl.rod.expression;

/**
 * @author longjie
 * 2021/5/12
 */
public interface Expression {

    /**
     * 表达式前缀识别
     */
    String EXPRESSION_PREFIX = "@{";

    /**
     * 表达式后缀识别
     */
    String EXPRESSION_SUFFIX = "}";

    /**
     * 当前表达式变量前缀,标识当前名称为变量
     */
    Character VARIABLE_PREFIX = '$';
}
