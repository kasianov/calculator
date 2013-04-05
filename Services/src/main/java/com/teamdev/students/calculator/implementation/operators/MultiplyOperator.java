package com.teamdev.students.calculator.implementation.operators;

import com.teamdev.students.calculator.implementation.Associativity;

import java.math.BigDecimal;

public class MultiplyOperator extends AbstractOperator {
    public MultiplyOperator() {
        super(2, Associativity.LEFT, 2, "*");
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) {
        if (arguments.length != getArgumentsCount()) {
            return null;
        }
        return arguments[1].multiply(arguments[0]);
    }
}
