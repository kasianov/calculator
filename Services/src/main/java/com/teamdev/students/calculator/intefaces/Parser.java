package com.teamdev.students.calculator.intefaces;

public interface Parser<Context> {
    boolean tryParse(Context context, boolean afterError);
}
