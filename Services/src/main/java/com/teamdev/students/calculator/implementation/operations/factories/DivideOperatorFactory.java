package com.teamdev.students.calculator.implementation.operations.factories;

import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.implementation.operations.DivideOperator;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;

public class DivideOperatorFactory implements OperationFactory<BigDecimal,MathematicalError> {
    private static final Operation<BigDecimal,MathematicalError> DIVIDE_OPERATOR = new DivideOperator();
    @Override
    public Operation<BigDecimal, MathematicalError> createOperation() {
        return DIVIDE_OPERATOR;
    }
}
