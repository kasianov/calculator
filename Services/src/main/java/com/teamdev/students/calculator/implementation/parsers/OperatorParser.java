package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.intefaces.Operator;
import com.teamdev.students.calculator.intefaces.Parser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * parser of all operators
 */
public class OperatorParser implements Parser<EvaluationContext> {
    private Map<String, Operator<BigDecimal>> stringOperatorMap = new HashMap<String, Operator<BigDecimal>>();

    public OperatorParser(List<Operator<BigDecimal>> operatorList) {
        for (Operator<BigDecimal> operator : operatorList) {
            stringOperatorMap.put(operator.getStringRepresentation(), operator);
        }
    }

    @Override
    public boolean tryParse(EvaluationContext evaluationContext, boolean afterError) {
        String expression = evaluationContext.getExpression();
        int position = evaluationContext.getCurrentPosition();
        if (position >= expression.length()) {
            return false;
        }
        String subExpression = expression.substring(position);
        for (String key : stringOperatorMap.keySet()) {
            if (subExpression.startsWith(key)) {
                if (!afterError) {
                    evaluationContext.pushOperator(stringOperatorMap.get(key));
                    evaluationContext.setCurrentPosition(position + key.length());
                } else {
                    evaluationContext.setErrorMessage("Unexpected operator [" + key + "]");
                }
                return true;
            }
        }
        return false;
    }
}
