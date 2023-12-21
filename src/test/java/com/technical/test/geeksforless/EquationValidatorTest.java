package com.technical.test.geeksforless;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class EquationValidatorTest {

    private final EquationValidator validator = new EquationValidator();

    private final String[] validEquations = {
            "2*x+5=17",
            "-1.3*5/x=1.2",
            "2*x*x=10",
            "2*(x+5+x)+5=10",
            "17=2*x+5"
    };
    private final String[] invalidEquations = {
            "2x+5=17a",
            "2*(x+5+x)+5=10)",
            "x5-=",
            "x-1"
    };
    @Test
    public void testValidateEquation_withValidEquations_ReturnsTrue() {
        for (String equation : validEquations) {
            assertTrue(validator.validateEquation(equation), "Failed for equation: " + equation);
        }
    }

    @Test
    public void testValidateEquation_InvalidEquations_ReturnsFalse() {
        for (String equation : invalidEquations) {
            assertFalse(validator.validateEquation(equation), "Failed for equation: " + equation);
        }
    }

    @Test
    public void testHasEqualsInTheMiddle_withValidEquations_ReturnsTrue() {
        for(String equation : validEquations) {
            assertTrue(validator.hasEqualsInTheMiddle(equation), "Failed for equation: " + equation);
        }
    }

    @Test
    public void testHasEqualsInTheMiddle_EquationWithoutEqualsInMiddle_ReturnsFalse() {
        assertFalse(validator.hasEqualsInTheMiddle("=2x+517"));
    }

    @Test
    public void testConsistsOfValidSymbols_withValidEquations_ReturnsTrue() {
        for (String equation : validEquations) {
            assertTrue(validator.consistsOfValidSymbols(equation), "Failed for equation: " + equation);
        }
    }

    @Test
    public void testConsistsOfValidSymbols_InvalidEquationSymbols_ReturnsFalse() {
        for (String equation : invalidEquations) {
            assertFalse(validator.consistsOfValidSymbols("3*x-a=5,4"));
        }
    }

    @Test
    public void testHasVariable_withValidEquations_ReturnsTrue() {
        for (String equation : validEquations) {
            assertTrue(validator.hasVariable(equation), "Failed for equation: " + equation);
        }
    }

    @Test
    public void testHasVariable_EquationWithoutVariable_ReturnsFalse() {
            assertFalse(validator.hasVariable("5-3=1+1"));

    }

    @Test
    public void testHasNumber_withValidEquations_ReturnsTrue() {
        for (String equation : validEquations) {
            assertTrue(validator.hasNumber(equation), "Failed for equation: " + equation);
        }
    }

    @Test
    public void testHasNumber_EquationWithoutNumber_ReturnsFalse() {
        assertFalse(validator.hasNumber("x=x"));
    }

    @Test
    public void testHasValidParentheses_withValidEquations_ReturnsTrue() {
        for (String equation : validEquations) {
            assertTrue(validator.hasValidParentheses(equation), "Failed for equation: " + equation);
        }
    }

    @Test
    public void testHasValidParentheses_InvalidEquationWithUnmatchedParentheses_ReturnsFalse() {
        assertFalse(validator.hasValidParentheses("(5-x)*2=2+x)"));
    }

    @Test
    public void testHasValidMathOperators_withValidEquations_ReturnsTrue() {
        for (String equation : validEquations) {
            assertTrue(validator.hasValidMathOperators(equation), "Failed for equation: " + equation);
        }
    }

    @Test
    public void testHasValidMathOperators_InvalidEquationWithInvalidOperatorSequence_ReturnsFalse() {
        assertFalse(validator.hasValidMathOperators("x-*5=25"));
    }
}