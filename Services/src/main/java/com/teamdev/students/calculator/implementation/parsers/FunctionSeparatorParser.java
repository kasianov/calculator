package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.implementation.MathematicalError;
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
            try{
                context.getEvaluator().pushFunctionSeparator();
                context.setCurrentPosition(position + 1);
            } catch (MathematicalError ex){
                context.setErrorMessage(ex.getMathematicalError());
                context.setInErrorState();
            }
        } else {
            context.setErrorMessage("Unexpected function separator");
        }
        return true;
    }
}
