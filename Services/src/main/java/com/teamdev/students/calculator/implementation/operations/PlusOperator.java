package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;

import java.math.BigDecimal;

public class PlusOperator extends AbstractOperation {
    public PlusOperator() {
        super(Associativity.LEFT, 2, 2, 1, "+");
        setArgumentsCount(2);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] bigDecimals) {
        if (bigDecimals.length != getArgumentsCount()) {
            return null;
        }
        return bigDecimals[1].add(bigDecimals[0]);
    }
}
