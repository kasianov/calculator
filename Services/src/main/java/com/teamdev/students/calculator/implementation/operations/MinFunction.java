package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class MinFunction extends AbstractOperation {
    public MinFunction() {
        super(Associativity.NONE, Integer.MAX_VALUE, 2, 2, "min");
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) throws MathematicalError {
        if (arguments.length < getMinimumArgumentsCount() || arguments.length > getMaximumArgumentsCount()) {
            throw new MathematicalError("Wrong number of arguments in 'min' function");
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
