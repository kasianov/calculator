package com.teamdev.students.calculator.intefaces;

import com.teamdev.students.calculator.implementation.Associativity;

public interface Operation<Result, OperationException extends Exception> {
    String getStringRepresentation();

    int getMinimumArgumentsCount();

    int getArgumentsCount();

    void setArgumentsCount(int argumentsCount);

    int getMaximumArgumentsCount();

    Result getResult(Result[] arguments) throws OperationException;

    int getPrecedence();

    Associativity getAssociativity();
}
