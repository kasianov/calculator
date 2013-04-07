package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class MaxFunction extends AbstractOperation {
    public MaxFunction() {
        super(Associativity.NONE, Integer.MAX_VALUE, 2, 2, "max");
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) throws MathematicalError {
        if (arguments.length < getMinimumArgumentsCount() || arguments.length > getMaximumArgumentsCount()) {
            throw new MathematicalError("Wrong number of arguments in 'max' function");
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
