package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.intefaces.Parser;

public class RightParenthesisParser implements Parser<EvaluationContext> {
    @Override
    public boolean tryParse(EvaluationContext evaluationContext, boolean afterError) {
        String expression = evaluationContext.getExpression();
        int position = evaluationContext.getCurrentPosition();
        if (position >= expression.length() || expression.charAt(position) != ')') {
            return false;
        }

        if (!afterError) {
            try{
                evaluationContext.getEvaluator().pushRightParenthesis();
                evaluationContext.setCurrentPosition(position + 1);
            } catch (MathematicalError ex){
                evaluationContext.setErrorMessage(ex.getMathematicalError());
                evaluationContext.setInErrorState();
            }
        } else {
            evaluationContext.setErrorMessage("Misplaced right parenthesis");
        }
        return true;
    }
}
