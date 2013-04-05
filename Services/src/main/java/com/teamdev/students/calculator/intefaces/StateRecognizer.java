package com.teamdev.students.calculator.intefaces;

public interface StateRecognizer<State extends Enum, Context> {

    boolean accept(State currentState, Context context, boolean afterError);

}
