package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.intefaces.Parser;

public class EndOfExpressionParser implements Parser<EvaluationContext> {
    @Override
    public boolean tryParse(EvaluationContext evaluationContext, boolean afterError) {
        if (evaluationContext.getCurrentPosition() >= evaluationContext.getExpression().length()) {
            if (afterError) {
                evaluationContext.setErrorMessage("Unexpected end of expression");
            }
            return true;
        }
        return false;
    }
}
