package com.teamdev.students.calculator.implementation.operations.factories;

import com.teamdev.students.calculator.implementation.MathematicalError;
import com.teamdev.students.calculator.implementation.operations.PowerOperator;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;

public class PowerOperatorFactory implements OperationFactory<BigDecimal,MathematicalError> {
    private static final Operation<BigDecimal,MathematicalError> POWER_OPERATOR = new PowerOperator();
    @Override
    public Operation<BigDecimal, MathematicalError> createOperation() {
        return POWER_OPERATOR;
    }
}
