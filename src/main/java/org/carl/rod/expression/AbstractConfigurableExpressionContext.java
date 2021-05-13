package org.carl.rod.expression;

import org.carl.rod.config.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longjie
 * 2021/5/12
 */
public abstract class AbstractConfigurableExpressionContext<T> implements ConfigurableExpressionContext<T> {

    private List<ExpressionParser> parserList;

    private ExpressionEngine<T> expressionEngine;

    public AbstractConfigurableExpressionContext() {
        parserList = new ArrayList<>();
    }

    @Override
    public void addExpressionParser(ExpressionParser expressionParser) {
        parserList.add(expressionParser);
    }

    @Override
    public T execute(String expression, PropertySource propertySource) {
        if (parserList.size() == 0) {
            return null;
        }

        for (ExpressionParser parser : parserList) {
            if (parser.isSupport(expression)) {
                ExpressionComponent expressionComponent = parser.parse(expression);
                return expressionEngine.execute(expressionComponent, propertySource);
            }
        }
        throw new NoSuitableExpressionParserFoundException("no suitable ExpressionParser found for expression %s", expression);
    }
}
