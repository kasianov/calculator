package com.teamdev.students.calculator.implementation.operations.factories;

import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.implementation.operations.PlusOperator;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;

public class PlusOperatorFactory implements OperationFactory<BigDecimal,MathematicalError> {
    private static final Operation<BigDecimal,MathematicalError> PLUS_OPERATOR = new PlusOperator();
    @Override
    public Operation<BigDecimal, MathematicalError> createOperation() {
        return PLUS_OPERATOR;
    }
}
