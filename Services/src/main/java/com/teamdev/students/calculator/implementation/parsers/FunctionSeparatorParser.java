package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.intefaces.Parser;

public class FunctionSeparatorParser implements Parser<EvaluationContext> {
    @Override
    public boolean tryParse(EvaluationContext context, boolean afterError) {
        String expression = context.getExpression();
        int position = context.getCurrentPosition();
        if (position >= expression.length() || expression.charAt(position) != ',') {
            return false;
        }

        if (!afterError) {
            if (context.getEvaluator().pushFunctionSeparator()) {
                context.setCurrentPosition(position + 1);
                return true;
            }
            return false;
        } else {
            context.setErrorMessage("Unexpected function separator");
        }
        return true;
    }
}
