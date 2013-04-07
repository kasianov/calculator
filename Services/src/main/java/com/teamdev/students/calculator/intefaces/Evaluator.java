package com.teamdev.students.calculator.intefaces;

import com.teamdev.students.calculator.implementation.MathematicalError;

public interface Evaluator<Result,
        MathOperation extends Operation<Result, MathematicalError>,
        MathError extends Exception> {
    void pushOperator(MathOperation operation);

    void pushLeftParenthesis();

    void pushRightParenthesis() throws MathError;

    void pushFunction(MathOperation operation) throws MathError;

    void pushFunctionSeparator() throws MathError;

    void pushValue(Result value) throws MathError;

    Result getResult() throws MathError;
}
