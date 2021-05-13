package org.carl.rod.expression;

import java.util.Collections;
import java.util.Set;

/**
 * @author longjie
 * 2021/5/12
 */
public class DefaultExpressionComponent extends AbstractExpressionComponent {

    private Set<String> variables;

    public DefaultExpressionComponent(String expression, Set<String> variables) {
        super(expression);
        this.variables = variables;
    }

    @Override
    public Set<String> getVariables() {
        return Collections.unmodifiableSet(variables);
    }
}
