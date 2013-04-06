package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;

import java.math.BigDecimal;

public class MinFunction extends AbstractOperation {
    public MinFunction() {
        super(Associativity.NONE, Integer.MAX_VALUE, 2, 2, "min");
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) {
        if (arguments.length < getMinimumArgumentsCount()) {
            return null;
        }
        BigDecimal min = arguments[0];
        for (BigDecimal argument : arguments) {
            if (min.compareTo(argument) > 0) {
                min = argument;
            }
        }
        return min;
    }
}
