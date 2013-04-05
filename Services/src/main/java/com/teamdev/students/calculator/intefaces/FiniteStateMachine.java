package com.teamdev.students.calculator.intefaces;

public interface FiniteStateMachine<
        State extends Enum,
        Matrix extends TransitionMatrix<State>,
        Result,
        Context extends FiniteStateMachineContext<State, Result>,
        Recognizer extends StateRecognizer<State, Context>,
        TransitionError extends Exception> {

    Result run(Context context) throws TransitionError;

    boolean moveForward(Context context);

    Matrix getTransitionMatrix();

    Recognizer getStateRecognizer();

    void deadlock(Context context) throws TransitionError;
}
