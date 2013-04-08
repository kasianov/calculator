package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;


public class UnaryMinusOperator extends AbstractOperation {
    public UnaryMinusOperator() {
        super(Associativity.NONE, 1, 1, 4, "-");
        setArgumentsCount(1);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) throws MathematicalError {
        if (arguments.length != getArgumentsCount()) {
            throw new MathematicalError("Wrong number of operands in 'unary minus' operator");
        }
        return arguments[0].multiply(new BigDecimal("-1"));
    }
}
