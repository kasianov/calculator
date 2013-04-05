package com.teamdev.students.calculator.implementation.operators;

import com.teamdev.students.calculator.implementation.Associativity;

import java.math.BigDecimal;

public class PlusOperator extends AbstractOperator {
    public PlusOperator() {
        super(2, Associativity.LEFT, 1, "+");
    }

    @Override
    public BigDecimal getResult(BigDecimal[] bigDecimals) {
        if (bigDecimals.length != getArgumentsCount()) {
            return null;
        }
        return bigDecimals[1].add(bigDecimals[0]);
    }
}
