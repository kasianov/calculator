package com.teamdev.students.calculator;

import com.teamdev.students.calculator.consoleui.CalculatorConsole;
import com.teamdev.students.calculator.implementation.Calculator;
import com.teamdev.students.calculator.swingui.CalculatorFrame;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-console")) {
            new CalculatorConsole(Calculator.createCalculator()).run();
        } else {
            new CalculatorFrame(Calculator.createCalculator());
        }
    }
}
