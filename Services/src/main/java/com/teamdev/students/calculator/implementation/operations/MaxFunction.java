package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;

import java.math.BigDecimal;

public class MaxFunction extends AbstractOperation {
    public MaxFunction() {
        super(Associativity.NONE, Integer.MAX_VALUE, 2, 2, "max");
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) {
        if (arguments.length < getMinimumArgumentsCount()) {
            return null;
        }
        BigDecimal max = arguments[0];
        for (BigDecimal argument : arguments) {
            if (max.compareTo(argument) < 0) {
                max = argument;
            }
        }
        return max;
    }
}
