package com.teamdev.students.calculator.implementation;

import com.teamdev.students.calculator.intefaces.*;

public abstract class AbstractFiniteStateMachine<
        State extends Enum,
        Matrix extends TransitionMatrix<State>,
        Result,
        Context extends FiniteStateMachineContext<State, Result,?>,
        Recognizer extends StateRecognizer<State, Context>,
        TransitionError extends Exception>
        implements FiniteStateMachine<
        State,
        Matrix,
        Context,
        Recognizer,
        TransitionError> {

    @Override
    public void run(Context context) throws TransitionError {
        Matrix matrix = getTransitionMatrix();
        context.setState(matrix.getStartState());
        while (!matrix.isFinishState(context.getState())) {
            if (!moveForward(context)) {
                deadlock(context);
            }
        }
    }

    @Override
    /**
     * moves through all possible states for current state
     * if no states was accepted then moves through all possible states
     * to find unexpected element
     */
    public boolean moveForward(Context context){
        Matrix matrix = getTransitionMatrix();
        Recognizer recognizer = getStateRecognizer();
        State currentState = context.getState();
        for (State possibleState : matrix.getPossibleStates(currentState)) {
            if (recognizer.accept(possibleState, context, false)) {
                if(context.isInErrorState()){
                    return false;
                }
                context.setState(possibleState);
                return true;
            }
        }
        for (State state : matrix.getAllPossibleStates()) {
            if (recognizer.accept(state, context, true)) {
                return false;
            }
        }
        context.errorOccurred();
        return false;
    }
}
