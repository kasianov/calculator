package com.teamdev.students.calculator.implementation.operators;

import com.teamdev.students.calculator.implementation.Associativity;

import java.math.BigDecimal;

public class PowerOperator extends AbstractOperator {
    public PowerOperator() {
        super(2, Associativity.LEFT, 3, "^");
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) {
        if (arguments.length != getArgumentsCount()) {
            return null;
        }
        return new BigDecimal(Math.pow(arguments[1].doubleValue(),arguments[0].doubleValue()));
    }
}
