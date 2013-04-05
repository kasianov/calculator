package com.teamdev.students.calculator.consoleui;

import com.teamdev.students.calculator.implementation.Calculator;
import com.teamdev.students.calculator.implementation.EvaluationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculatorConsole {
    private Calculator calculator;

    public CalculatorConsole(Calculator calculator) {
        this.calculator = calculator;
    }

    public void run() {
        System.out.println("Enter 'exit' to exit");
        System.out.println("Enter math expression");
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        try {
            while (!(line = bufferedReader.readLine()).equals("exit")) {
                try {
                    System.out.println("Result is: " + calculator.evaluate(line));
                } catch (EvaluationException exception) {
                    System.out.println(exception.getMessage());
                    System.out.println("Expression: " + exception.getExpression());
                    System.out.println("Position: " + (exception.getErrorPosition() + 1));
                }
                System.out.println("\nEnter math expression");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
