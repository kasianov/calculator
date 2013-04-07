package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class SqrtFunction extends AbstractOperation {
    public SqrtFunction() {
        super(Associativity.NONE, 1, 1, 2, "sqrt");
        setArgumentsCount(1);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) throws MathematicalError {
        if (arguments.length != getArgumentsCount()) {
            throw new MathematicalError("Wrong number of arguments in 'sqrt' function");
        }
        if (arguments[0].compareTo(new BigDecimal("0")) < 0) {
            throw new MathematicalError("Invalid value of argument in 'sqrt' function: [" + arguments[0] + "]");
        }
        return new BigDecimal(Math.sqrt(arguments[0].doubleValue()));
    }
}
