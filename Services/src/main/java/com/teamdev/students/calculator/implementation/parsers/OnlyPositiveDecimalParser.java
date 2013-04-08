package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.intefaces.Parser;

import java.math.BigDecimal;

public class OnlyPositiveDecimalParser implements Parser<EvaluationContext> {
    @Override
    public boolean tryParse(EvaluationContext evaluationContext, boolean afterError) {
        String expression = evaluationContext.getExpression();
        int position = evaluationContext.getCurrentPosition();
        int count = 1;
        BigDecimal tempNumber;
        BigDecimal number = null;
        while (true) {
            if (position + count > expression.length()) {
                break;
            }
            try {
                tempNumber = new BigDecimal(expression.substring(position, position + count));
                number = tempNumber;
                ++count;
            } catch (Exception ex) {
                break;
            }
        }
        --count;
        if (number != null) {
            if (!afterError) {
                try{
                    evaluationContext.getEvaluator().pushValue(number);
                    evaluationContext.setCurrentPosition(position + count);
                } catch (MathematicalError ex){
                    evaluationContext.setErrorMessage(ex.getMathematicalError());
                    evaluationContext.setInErrorState();
                }
            } else {
                evaluationContext.setErrorMessage("Unexpected number token");
            }
        } else{
            return false;
        }
        return true;
    }
}
