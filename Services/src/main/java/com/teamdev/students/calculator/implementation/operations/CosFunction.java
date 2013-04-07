package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class CosFunction extends AbstractOperation {
    public CosFunction() {
        super(Associativity.NONE, 1, 1, 2, "cos");
        setArgumentsCount(1);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) throws MathematicalError {
        if (arguments.length != getArgumentsCount()) {
            throw new MathematicalError("Wrong number of arguments in 'cos' function");
        }
        return new BigDecimal(Math.cos(arguments[0].doubleValue()));
    }
}
