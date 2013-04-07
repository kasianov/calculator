package com.teamdev.students.calculator.implementation.operations.factories;

import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.implementation.operations.BinaryMinusOperator;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;

public class BinaryMinusOperatorFactory implements OperationFactory<BigDecimal,MathematicalError> {
    private static final Operation<BigDecimal,MathematicalError> BINARY_MINUS_OPERATOR = new BinaryMinusOperator();
    @Override
    public Operation<BigDecimal, MathematicalError> createOperation() {
        return BINARY_MINUS_OPERATOR;
    }
}
