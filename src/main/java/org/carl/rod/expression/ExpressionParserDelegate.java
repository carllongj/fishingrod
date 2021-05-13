package org.carl.rod.expression;

import java.util.HashSet;
import java.util.Set;

/**
 * @author longjie
 * 2021/5/12
 */
class ExpressionParserDelegate {


    /**
     * 执行表达式解析
     *
     * @param expression 解析后的表达式
     * @return 返回表达式
     */
    public ExpressionComponent parseExpression(String expression) {
        String originExpression = expression;
        expression = expression.substring(Expression.EXPRESSION_PREFIX.length(), expression.length() - 1);
        Set<String> variables = extractVariables(expression);
        return new DefaultExpressionComponent(originExpression, variables);
    }

    /**
     * 提取当前表达式中的所有变量名,对于变量转义则使用 $$ 对$ 进行转义
     *
     * @param expression 指定的表达式
     * @return 返回对应的变量名称集合
     */
    private Set<String> extractVariables(String expression) {
        int prefix = 0;
        boolean variableStart = false;
        Set<String> variables = new HashSet<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == Expression.VARIABLE_PREFIX) {
                if (prefix == i - 1) {
                    continue;
                }
                if (!variableStart) {
                    prefix = i;
                    variableStart = true;
                } else if (variableStart) {
                    variables.add(expression.substring(prefix + 1, i));
                    variableStart = false;
                }
            }
        }
        return variables;
    }
}
