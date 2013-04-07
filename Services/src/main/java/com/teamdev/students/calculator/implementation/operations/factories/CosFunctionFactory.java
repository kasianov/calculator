package com.teamdev.students.calculator.implementation.operations.factories;

import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.implementation.operations.CosFunction;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;

public class CosFunctionFactory implements OperationFactory<BigDecimal,MathematicalError> {
    private static final Operation<BigDecimal,MathematicalError> COS_FUNCTION = new CosFunction();
    @Override
    public Operation<BigDecimal, MathematicalError> createOperation() {
        return COS_FUNCTION;
    }
}
