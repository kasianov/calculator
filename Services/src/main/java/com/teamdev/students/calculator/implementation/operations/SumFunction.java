package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class SumFunction extends AbstractOperation {
    public SumFunction() {
        super(Associativity.NONE, Integer.MAX_VALUE, 2, 2, "sum");
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) throws MathematicalError {
        if (arguments.length < getMinimumArgumentsCount() || arguments.length > getMaximumArgumentsCount()) {
            throw new MathematicalError("Wrong number of arguments in 'sum' function");
        }
        BigDecimal sum = new BigDecimal(0);
        for (BigDecimal argument : arguments) {
            sum = sum.add(argument);
        }
        return sum;
    }
}
