package com.teamdev.students.calculator.implementation;

public enum EvaluationState {
    START,
    NUMBER,
    BINARY_OPERATOR,
    FUNCTION,
    FUNCTION_SEPARATOR,
    LEFT_PARENTHESIS,
    LEFT_PARENTHESIS_AFTER_FUNCTION,
    RIGHT_PARENTHESIS,
    FINISH
}
