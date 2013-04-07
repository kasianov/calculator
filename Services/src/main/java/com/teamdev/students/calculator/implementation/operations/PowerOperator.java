package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class PowerOperator extends AbstractOperation {
    public PowerOperator() {
        super(Associativity.LEFT, 2, 2, 3, "^");
        setArgumentsCount(2);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) throws MathematicalError {
        if (arguments.length != getArgumentsCount()) {
            throw new MathematicalError("Wrong number of operands in 'power' operator");
        }
        return new BigDecimal(Math.pow(arguments[1].doubleValue(), arguments[0].doubleValue()));
    }
}
