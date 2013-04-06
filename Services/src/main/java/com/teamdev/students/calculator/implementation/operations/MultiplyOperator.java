package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;

import java.math.BigDecimal;

public class MultiplyOperator extends AbstractOperation {
    public MultiplyOperator() {
        super(Associativity.LEFT, 2, 2, 2, "*");
        setArgumentsCount(2);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) {
        if (arguments.length != getArgumentsCount()) {
            return null;
        }
        return arguments[1].multiply(arguments[0]);
    }
}
