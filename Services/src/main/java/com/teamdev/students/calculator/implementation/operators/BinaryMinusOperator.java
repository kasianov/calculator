package com.teamdev.students.calculator.implementation.operators;

import com.teamdev.students.calculator.implementation.Associativity;

import java.math.BigDecimal;

public class BinaryMinusOperator extends AbstractOperator {
    public BinaryMinusOperator() {
        super(2, Associativity.LEFT, 1, "-");
    }

    @Override
    public BigDecimal getResult(BigDecimal[] bigDecimals) {
        if (bigDecimals.length != getArgumentsCount()) {
            return null;
        }
        return bigDecimals[1].subtract(bigDecimals[0]);
    }
}
