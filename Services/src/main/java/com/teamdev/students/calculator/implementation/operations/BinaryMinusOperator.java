package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class BinaryMinusOperator extends AbstractOperation {
    public BinaryMinusOperator() {
        super(Associativity.LEFT, 2, 2, 1, "-");
        setArgumentsCount(2);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] bigDecimals) throws MathematicalError {
        if (bigDecimals.length != getArgumentsCount()) {
            throw new MathematicalError("Wrong number of operands in 'binary minus' operator");
        }
        return bigDecimals[1].subtract(bigDecimals[0]);
    }
}
