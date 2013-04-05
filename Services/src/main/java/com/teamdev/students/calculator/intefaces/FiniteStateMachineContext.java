package com.teamdev.students.calculator.intefaces;

public interface FiniteStateMachineContext<State extends Enum,
        Result> {

    State getState();

    void setState(State state);

    void errorOccurred();

    Result getResult();
}
