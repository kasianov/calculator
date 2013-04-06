package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.intefaces.Parser;

public class LeftParenthesisParser implements Parser<EvaluationContext> {
    @Override
    public boolean tryParse(EvaluationContext evaluationContext, boolean afterError) {
        String expression = evaluationContext.getExpression();
        int position = evaluationContext.getCurrentPosition();
        if (position >= expression.length() || expression.charAt(position) != '(') {
            return false;
        }

        if (!afterError) {
            evaluationContext.getEvaluator().pushLeftParenthesis();
            evaluationContext.setCurrentPosition(position + 1);
        } else {
            evaluationContext.setErrorMessage("Unexpected left parenthesis");
        }
        return true;
    }
}
