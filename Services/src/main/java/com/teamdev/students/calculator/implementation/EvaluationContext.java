package com.teamdev.students.calculator.implementation;

import com.teamdev.students.calculator.intefaces.FiniteStateMachineContext;

import java.math.BigDecimal;

public class EvaluationContext implements FiniteStateMachineContext<EvaluationState, BigDecimal,MathematicalError> {

    private EvaluationState state;
    private int currentPosition;
    private String expression;
    private String errorMessage;
    private CalculatorEvaluator evaluator;
    private boolean inErrorState;

    public EvaluationContext(String expression, CalculatorEvaluator evaluator) {
        this.expression = expression;
        this.evaluator = evaluator;
    }

    public CalculatorEvaluator getEvaluator() {
        return evaluator;
    }

    @Override
    /**
     * this method is invoked from state machine loop when no state was recognized
     */
    public void errorOccurred() {
        if (getCurrentPosition() < getExpression().length()) {
            setErrorMessage("Unexpected token [" + getExpression().charAt(getCurrentPosition()) + "]");
        } else {
            setErrorMessage("empty input string");
        }
    }

    @Override
    public void setInErrorState() {
        inErrorState = true;
    }

    @Override
    public boolean isInErrorState() {
        return inErrorState;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * gets the input expression of the context
     *
     * @return - context expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * gets current position
     *
     * @return - current position
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * sets current position
     *
     * @param currentPosition - value to set
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    /**
     * gets current state of the context
     */
    public EvaluationState getState() {
        return state;
    }

    @Override
    /**
     * sets current state of the context
     */
    public void setState(EvaluationState evaluationState) {
        this.state = evaluationState;
    }

    @Override
    /**
     * evaluates a result of the input expression
     */
    public BigDecimal getResult() throws MathematicalError{
        return evaluator.getResult();
    }


}
