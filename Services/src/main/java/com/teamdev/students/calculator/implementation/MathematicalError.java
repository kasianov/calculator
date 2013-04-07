package com.teamdev.students.calculator.implementation;

public class MathematicalError extends Exception {
    private String mathematicalError;

    public MathematicalError(String mathematicalError) {
        this.mathematicalError = mathematicalError;
    }

    public String getMathematicalError() {
        return mathematicalError;
    }
}
