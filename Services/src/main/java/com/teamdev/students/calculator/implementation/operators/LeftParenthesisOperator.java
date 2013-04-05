package com.teamdev.students.calculator.implementation.operators;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.intefaces.Operator;

import java.math.BigDecimal;

/**
 * this operator does nothing but represents a left parenthesis (method isLeftParenthesis() returns true)
 */
public class LeftParenthesisOperator implements Operator<BigDecimal> {
    @Override
    public int getArgumentsCount() {
        return 0;
    }

    @Override
    public String getStringRepresentation() {
        return null;
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
        return true;
    }

    @Override
    public boolean isRightParenthesis() {
        return false;
    }
}
