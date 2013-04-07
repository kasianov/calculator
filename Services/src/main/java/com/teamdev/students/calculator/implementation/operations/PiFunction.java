package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class PiFunction extends AbstractOperation {
    public PiFunction() {
        super(Associativity.NONE, 0, 0, 2, "pi");
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) throws MathematicalError {
        if (arguments.length != getArgumentsCount()) {
            throw new MathematicalError("Wrong number of arguments in 'pi' function");
        }
        return new BigDecimal("3.14159");
    }
}
