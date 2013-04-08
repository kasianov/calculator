package com.teamdev.students.calculator.implementation;

public enum EvaluationState {
    START,
    NUMBER,
    ONLY_POSITIVE_NUMBER,
    BINARY_OPERATOR,
    UNARY_MINUS_OPERATOR,
    FUNCTION,
    FUNCTION_SEPARATOR,
    LEFT_PARENTHESIS,
    LEFT_PARENTHESIS_AFTER_FUNCTION,
    RIGHT_PARENTHESIS,
    FINISH
}
