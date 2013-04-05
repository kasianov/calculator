package com.teamdev.students.calculator.implementation.operators;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.intefaces.Operator;

import java.math.BigDecimal;

/**
 * abstract class for all operators
 */
public abstract class AbstractOperator
        implements Operator<BigDecimal> {
    private Associativity associativity;
    private String stringRepresentation;
    private int precedence;
    private int argumentsCount;

    protected AbstractOperator(int argumentsCount, Associativity associativity, int precedence, String stringRepresentation) {
        this.argumentsCount = argumentsCount;
        this.associativity = associativity;
        this.precedence = precedence;
        this.stringRepresentation = stringRepresentation;
    }

    @Override
    public int getArgumentsCount() {
        return argumentsCount;
    }

    @Override
    public Associativity getAssociativity() {
        return associativity;
    }

    @Override
    public String getStringRepresentation() {
        return stringRepresentation;
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }

    @Override
    public boolean isLeftParenthesis() {
        return false;
    }

    @Override
    public boolean isRightParenthesis() {
        return false;
    }
}
