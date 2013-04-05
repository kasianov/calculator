package com.teamdev.students.calculator.implementation;

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

    public static Calculator createCalculator(){
        return new Calculator(EvaluationMatrix.createEvaluationMatrix(),EvaluationStateRecognizer.createEvaluationStateRecognizer());
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
