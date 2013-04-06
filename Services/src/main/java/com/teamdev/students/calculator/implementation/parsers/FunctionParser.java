package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;
import java.util.List;

public class FunctionParser extends BaseOperationParser {
    public FunctionParser(List<Operation<BigDecimal>> operations) {
        super(operations);
    }

    @Override
    protected boolean doPush(EvaluationContext context, Operation<BigDecimal> operation) {
        return context.getEvaluator().pushFunction(operation);
    }
}
