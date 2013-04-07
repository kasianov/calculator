package com.teamdev.students.calculator.implementation.operations.factories;

import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.implementation.operations.MultiplyOperator;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;

public class MultiplyOperatorFactory implements OperationFactory<BigDecimal,MathematicalError> {
    private static final Operation<BigDecimal,MathematicalError> MULTIPLY_OPERATOR = new MultiplyOperator();
    @Override
    public Operation<BigDecimal, MathematicalError> createOperation() {
        return MULTIPLY_OPERATOR;
    }
}
