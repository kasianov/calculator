package com.teamdev.students.calculator.intefaces;

public interface FiniteStateMachineContext<State extends Enum,
        Result,
        ResultError extends Exception> {

    State getState();

    void setState(State state);

    void errorOccurred();

    void setInErrorState();

    boolean isInErrorState();

    Result getResult() throws ResultError;
}
