package com.technical.test.geeksforless;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Data
@Component
public class EquationValidator implements Validator {
    public  boolean validateEquation(String equation) {
        return  isNotEmpty(equation)
                && consistsOfValidSymbols(equation)
                && hasNumber(equation)
                && hasVariable(equation)
                && hasEqualsInTheMiddle(equation)
                && hasValidParentheses(equation)
                && hasValidMathOperators(equation);
    }
    public boolean isNotEmpty(String equation) {
        return equation != null && !equation.isEmpty() && !equation.isBlank();
    }
    public boolean hasEqualsInTheMiddle(String equation) {
        return equation.indexOf('=') != -1 && equation.indexOf('=') != 0 && equation.indexOf('=') != equation.length() - 1;
    }
    public boolean consistsOfValidSymbols(String equation) {
        String regex = "^[0-9x.+\\-*/=()]*$";
        return equation.matches(regex);
    }

    public  boolean hasVariable(String equation) {
        String variableRegex = "^(?!.*xx).*[x].*$";
        return equation.matches(variableRegex);
    }

    public boolean hasNumber(String equation) {
        String numberRegex = ".*[-+]?\\d*\\.?\\d+.*";
        return equation.matches(numberRegex);
    }

    public  boolean hasValidParentheses(String equation) {
        for(String side : equation.split("=")) {
            int count = 0;
            for (char ch : equation.toCharArray()) {
                if (ch == '(') {
                    count++;
                }
                if (ch == ')') {
                    if (count == 0) {
                        return false;
                    }
                    count--;
                }
            }
            if(count != 0) return false;
        }
        return !equation.contains("()") && !equation.contains(")(");
    }

    public boolean hasValidMathOperators(String equation) {
        char prevChar = equation.charAt(0);
        char prevOperator = '\0';
        for (int i = 1; i < equation.length(); i++) {
            char currentChar = equation.charAt(i);
            if (isInvalidOperatorSequence(prevChar, currentChar)) {
                return false;
            }
            prevChar = currentChar;

        }
        return true;
    }

    private static boolean isInvalidOperatorSequence(char prevOperator, char currentChar) {
        return (prevOperator == '+' || prevOperator == '-' || prevOperator == '*' || prevOperator == '/') && (currentChar == '*' || currentChar == '/');
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Equation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Equation equation = (Equation) target;
            if (!validateEquation(equation.getExpression())) {
                errors.rejectValue("expression", "InvalidExpression", "Invalid format of equation");
            }
    }
}
