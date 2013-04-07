package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class MultiplyOperator extends AbstractOperation {
    public MultiplyOperator() {
        super(Associativity.LEFT, 2, 2, 2, "*");
        setArgumentsCount(2);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) throws MathematicalError {
        if (arguments.length != getArgumentsCount()) {
            throw new MathematicalError("Wrong number of operands in 'multiply' operator");
        }
        return arguments[1].multiply(arguments[0]);
    }
}
