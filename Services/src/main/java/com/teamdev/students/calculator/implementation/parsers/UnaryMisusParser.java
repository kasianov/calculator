package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.implementation.operations.UnaryMinusOperator;
import com.teamdev.students.calculator.intefaces.Parser;

public class UnaryMisusParser implements Parser<EvaluationContext> {
    @Override
    public boolean tryParse(EvaluationContext context, boolean afterError) {
        String expression = context.getExpression();
        int position = context.getCurrentPosition();
        if (position >= expression.length() || expression.charAt(position) != '-') {
            return false;
        }

        if (!afterError) {
            context.getEvaluator().pushOperator(new UnaryMinusOperator());
            context.setCurrentPosition(position + 1);
        } else {
            context.setErrorMessage("Unexpected minus operator");
        }
        return true;
    }
}
