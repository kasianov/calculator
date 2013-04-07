package com.teamdev.students.calculator.implementation.operations.factories;

import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.implementation.operations.MaxFunction;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;

public class MaxFunctionFactory implements OperationFactory<BigDecimal,MathematicalError> {
    @Override
    public Operation<BigDecimal, MathematicalError> createOperation() {
        return new MaxFunction();
    }
}
