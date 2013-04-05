package com.teamdev.students.calculator.intefaces;

import com.teamdev.students.calculator.implementation.Associativity;

public interface Operator<Result> {

    String getStringRepresentation();

    int getArgumentsCount();

    Result getResult(Result[] arguments);

    int getPrecedence();

    Associativity getAssociativity();

    boolean isLeftParenthesis();

    boolean isRightParenthesis();
}
