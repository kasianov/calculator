package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;

import java.math.BigDecimal;

public class SinFunction extends AbstractOperation {
    public SinFunction() {
        super(Associativity.NONE, 1, 1, 2, "sin");
        setArgumentsCount(1);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) {
        if (arguments.length != getArgumentsCount()) {
            return null;
        }
        return new BigDecimal(Math.sin(arguments[0].doubleValue()));
    }
}
