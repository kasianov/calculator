package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.intefaces.Operation;
import com.teamdev.students.calculator.intefaces.Parser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class BaseOperationParser implements Parser<EvaluationContext> {
    private Map<String, Operation<BigDecimal>> stringOperationMap = new HashMap<String, Operation<BigDecimal>>();

    protected BaseOperationParser(List<Operation<BigDecimal>> operations) {
        for (Operation<BigDecimal> operation : operations) {
            stringOperationMap.put(operation.getStringRepresentation(), operation);
        }
    }

    protected abstract boolean doPush(EvaluationContext context, Operation<BigDecimal> operation);

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
                    if(!doPush(evaluationContext,stringOperationMap.get(key))){
                        return false;
                    }
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
