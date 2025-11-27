package hello;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.jexl3.*;

public class CalculatorTests {

    private Object eval(String expr) {
        JexlEngine jexl = new JexlBuilder().create();
        JexlExpression e = jexl.createExpression(expr);
        return e.evaluate(null);
    }

    @Test
    void testValidExpression() {
        Object result = eval("1+1");
        assertEquals(2, result); 
    }

    @Test
    void testFailingExpression() {
        Object result = eval("5-1");
        assertEquals(2, result); 
    }
}
