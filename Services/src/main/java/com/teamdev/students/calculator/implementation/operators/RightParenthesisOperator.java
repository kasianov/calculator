package com.teamdev.students.calculator.implementation.operators;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.intefaces.Operator;

import java.math.BigDecimal;

/**
 * this operator does nothing but represents a right parenthesis (method isRightParenthesis() returns true)
 */
public class RightParenthesisOperator implements Operator<BigDecimal> {
    @Override
    public String getStringRepresentation() {
        return null;
    }

    @Override
    public int getArgumentsCount() {
        return 0;
    }

    @Override
    public BigDecimal getResult(BigDecimal[] arguments) {
        return null;
    }

    @Override
    public int getPrecedence() {
        return 0;
    }

    @Override
    public Associativity getAssociativity() {
        return Associativity.NONE;
    }

    @Override
    public boolean isLeftParenthesis() {
        return false;
    }

    @Override
    public boolean isRightParenthesis() {
        return true;
    }
}
