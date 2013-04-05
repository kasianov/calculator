package com.teamdev.students.calculator.implementation;

public class EvaluationException extends Exception {
    private int errorPosition;
    private String expression;

    public EvaluationException(int errorPosition, String expression, String message) {
        super(message);
        this.errorPosition = errorPosition;
        this.expression = expression;
    }

    public int getErrorPosition() {
        return errorPosition;
    }

    public String getExpression(){
        return expression;
    }
}
