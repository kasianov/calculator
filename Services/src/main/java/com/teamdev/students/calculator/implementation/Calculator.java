package com.teamdev.students.calculator.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Calculator extends AbstractFiniteStateMachine<
        EvaluationState,
        EvaluationMatrix,
        BigDecimal,
        EvaluationContext,
        EvaluationStateRecognizer,
        EvaluationException> {

    private EvaluationMatrix evaluationMatrix;
    private EvaluationStateRecognizer stateRecognizer;

    public Calculator(EvaluationMatrix evaluationMatrix, EvaluationStateRecognizer stateRecognizer) {
        this.evaluationMatrix = evaluationMatrix;
        this.stateRecognizer = stateRecognizer;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator(
                EvaluationMatrix.createEvaluationMatrix(),
                EvaluationStateRecognizer.createEvaluationStateRecognizer());
        input(calculator);
    }

    private static void input(Calculator calculator) {
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

    @Override
    public void deadlock(EvaluationContext context) throws EvaluationException {
        throw new EvaluationException(context.getCurrentPosition(), context.getExpression(), context.getErrorMessage());
    }

    @Override
    public EvaluationMatrix getTransitionMatrix() {
        return evaluationMatrix;
    }

    @Override
    public EvaluationStateRecognizer getStateRecognizer() {
        return stateRecognizer;
    }

    public BigDecimal evaluate(String expression) throws EvaluationException {
        return run(new EvaluationContext(expression.replaceAll(" ", "")));
    }
}
