package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;

import java.math.BigDecimal;

public class BinaryMinusOperator extends AbstractOperation {
    public BinaryMinusOperator() {
        super(Associativity.LEFT, 2, 2, 1, "-");
        setArgumentsCount(2);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] bigDecimals) {
        if (bigDecimals.length != getArgumentsCount()) {
            return null;
        }
        return bigDecimals[1].subtract(bigDecimals[0]);
    }
}
