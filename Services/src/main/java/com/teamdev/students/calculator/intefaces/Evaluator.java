package com.teamdev.students.calculator.intefaces;

public interface Evaluator<Result,MathOperation extends Operation<Result>> {
    void pushOperator(MathOperation operation);
    void pushLeftParenthesis();
    boolean pushRightParenthesis();
    boolean pushFunction(MathOperation operation);
    boolean pushFunctionSeparator();
    boolean pushValue(Result value);
    Result getResult();
}
