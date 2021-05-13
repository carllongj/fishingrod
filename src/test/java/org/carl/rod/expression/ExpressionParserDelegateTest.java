package org.carl.rod.expression;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author longjie
 * 2021/5/12
 */
public class ExpressionParserDelegateTest {

    @Test
    public void testParseExpression() {
        ExpressionParserDelegate delegate = new ExpressionParserDelegate();
        ExpressionComponent component = delegate.parseExpression("@{$$jps$ksl$ - 120}");
        Assert.assertEquals(component.getVariables().size(), 1);
    }

}
