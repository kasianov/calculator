package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class PlusOperator extends AbstractOperation {
    public PlusOperator() {
        super(Associativity.LEFT, 2, 2, 1, "+");
        setArgumentsCount(2);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] bigDecimals) throws MathematicalError {
        if (bigDecimals.length != getArgumentsCount()) {
            throw new MathematicalError("Wrong number of operands in 'plus' operator");
        }
        return bigDecimals[1].add(bigDecimals[0]);
    }
}
