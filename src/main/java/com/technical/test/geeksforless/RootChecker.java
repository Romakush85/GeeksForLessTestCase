package com.technical.test.geeksforless;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import lombok.Data;
import org.springframework.stereotype.Component;



@Component
@Data
public class RootChecker {

    private static Double EPSILON = 1.0e-9;

    public boolean isRootOfEquation(Equation equation, Double x)  {
        var expressions = equation.getExpression().split("=");
        String leftSide = expressions[0].replace("x", String.valueOf(x));
        String rightSide  = expressions[1].replace("x", String.valueOf(x));

        DoubleEvaluator evaluator = new DoubleEvaluator();

        Double leftResult = evaluator.evaluate(leftSide);
        Double rightResult = evaluator.evaluate(rightSide);

        return Math.abs(leftResult - rightResult) < EPSILON;
    }
}
