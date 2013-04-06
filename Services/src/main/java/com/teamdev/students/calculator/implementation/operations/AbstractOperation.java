package com.teamdev.students.calculator.implementation.operations;

import com.teamdev.students.calculator.implementation.Associativity;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;

/**
 * abstract class for all operations (functions and operators)
 */
public abstract class AbstractOperation
        implements Operation<BigDecimal> {

    private Associativity associativity;
    private String stringRepresentation;
    private int precedence;
    private int argumentsCount;
    private int minimumArgumentsCount;
    private int maximumArgumentsCount;

    protected AbstractOperation(Associativity associativity, int maximumArgumentsCount, int minimumArgumentsCount, int precedence, String stringRepresentation) {
        this.associativity = associativity;
        this.maximumArgumentsCount = maximumArgumentsCount;
        this.minimumArgumentsCount = minimumArgumentsCount;
        this.precedence = precedence;
        this.stringRepresentation = stringRepresentation;
    }

    @Override
    public int getArgumentsCount() {
        return argumentsCount;
    }

    public void setArgumentsCount(int argumentsCount) {
        this.argumentsCount = argumentsCount;
    }

    @Override
    public Associativity getAssociativity() {
        return associativity;
    }

    @Override
    public int getMaximumArgumentsCount() {
        return maximumArgumentsCount;
    }

    @Override
    public int getMinimumArgumentsCount() {
        return minimumArgumentsCount;
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }

    @Override
    public String getStringRepresentation() {
        return stringRepresentation;
    }

}
