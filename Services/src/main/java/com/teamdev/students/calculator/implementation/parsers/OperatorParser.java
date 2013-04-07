package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.implementation.operations.factories.OperationFactory;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class OperatorParser extends BaseOperationParser {
    public OperatorParser(Map<String,OperationFactory<BigDecimal,MathematicalError>> stringOperationFactoryMap) {
        super(stringOperationFactoryMap);
    }

    @Override
    protected boolean doPush(EvaluationContext context, Operation<BigDecimal, MathematicalError> operation) {
        context.getEvaluator().pushOperator(operation);
        return true;
    }
}
