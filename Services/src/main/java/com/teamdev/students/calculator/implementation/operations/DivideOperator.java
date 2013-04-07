package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.implementation.MathematicalError;

import java.math.BigDecimal;

public class DivideOperator extends AbstractOperation {
    public DivideOperator() {
        super(Associativity.LEFT, 2, 2, 2, "/");
        setArgumentsCount(2);
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) throws MathematicalError {
        if (arguments.length != getArgumentsCount()) {
            throw new MathematicalError("Wrong number of operands in 'divide' operator");
        }
        if (arguments[0].equals(new BigDecimal("0"))) {
            throw new MathematicalError("Division by zero error");
        }
        try {
            //more precise result, but if input for example is 10/3 then exception is thrown
            return arguments[1].divide(arguments[0]);
        } catch (Exception ex) {
            //if 10/3 then exception will not be thrown
            return new BigDecimal(arguments[1].doubleValue() / arguments[0].doubleValue());
        }
    }
}
