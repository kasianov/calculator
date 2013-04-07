package com.teamdev.students.calculator.intefaces;

public interface FiniteStateMachine<
        State extends Enum,
        Matrix extends TransitionMatrix<State>,
        Context extends FiniteStateMachineContext<State, ?, ?>,
        Recognizer extends StateRecognizer<State, Context>,
        TransitionError extends Exception> {

    void run(Context context) throws TransitionError;

    boolean moveForward(Context context);

    Matrix getTransitionMatrix();

    Recognizer getStateRecognizer();

    void deadlock(Context context) throws TransitionError;
}
