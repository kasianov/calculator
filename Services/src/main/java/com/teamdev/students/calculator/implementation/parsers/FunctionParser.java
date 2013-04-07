package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;
import java.util.List;

public class FunctionParser extends BaseOperationParser {
    public FunctionParser(List<Operation<BigDecimal, MathematicalError>> operations) {
        super(operations);
    }

    @Override
    protected boolean doPush(EvaluationContext context, Operation<BigDecimal, MathematicalError> operation) {
        try {
            context.getEvaluator().pushFunction(operation);
        } catch (MathematicalError ex) {
            context.setErrorMessage(ex.getMathematicalError());
            context.setInErrorState();
        }
        return true;
    }
}
