package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.intefaces.Parser;

import java.math.BigDecimal;


public class DecimalParser implements Parser<EvaluationContext> {
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
                if (count == 1 && expression.charAt(position) == '-') {
                    ++count;
                    continue;
                }
                break;
            }
        }
        --count;
        if (number != null) {
            if (!afterError) {
                evaluationContext.pushNumber(new BigDecimal(expression.substring(position, position + count)));
                evaluationContext.setCurrentPosition(position + count);
            } else {
                evaluationContext.setErrorMessage("Unexpected number token");
            }
            return true;
        }
        return false;
    }
}
