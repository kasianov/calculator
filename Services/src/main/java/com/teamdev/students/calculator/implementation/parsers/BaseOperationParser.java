package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.intefaces.Operation;
import com.teamdev.students.calculator.intefaces.Parser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * base class for function and operator parsers
 */
public abstract class BaseOperationParser implements Parser<EvaluationContext> {
    private Map<String, Operation<BigDecimal, MathematicalError>> stringOperationMap = new HashMap<String, Operation<BigDecimal, MathematicalError>>();

    protected BaseOperationParser(List<Operation<BigDecimal, MathematicalError>> operations) {
        for (Operation<BigDecimal, MathematicalError> operation : operations) {
            stringOperationMap.put(operation.getStringRepresentation(), operation);
        }
    }

    protected abstract boolean doPush(EvaluationContext context, Operation<BigDecimal, MathematicalError> operation);

    @Override
    public boolean tryParse(EvaluationContext evaluationContext, boolean afterError) {
        String expression = evaluationContext.getExpression();
        int position = evaluationContext.getCurrentPosition();
        if (position >= expression.length()) {
            return false;
        }
        String subExpression = expression.substring(position);
        for (String key : stringOperationMap.keySet()) {
            if (subExpression.startsWith(key)) {
                if (!afterError) {
                    doPush(evaluationContext, stringOperationMap.get(key));
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
