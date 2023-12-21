package com.technical.test.geeksforless;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RootCheckerTest {
    @Autowired
    private RootChecker checker;
    @Test
    public void testIsRootOfEquation() {
        Equation equation = new Equation();
        equation.setExpression("2*x+5=8+x");

        assertTrue(checker.isRootOfEquation(equation, 3.0));

        assertFalse(checker.isRootOfEquation(equation, 5.0));
    }
}