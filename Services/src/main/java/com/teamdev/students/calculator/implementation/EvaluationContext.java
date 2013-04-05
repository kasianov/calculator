package com.teamdev.students.calculator.implementation;

import com.teamdev.students.calculator.intefaces.FiniteStateMachineContext;
import com.teamdev.students.calculator.intefaces.Operator;

import java.math.BigDecimal;
import java.util.*;

public class EvaluationContext implements FiniteStateMachineContext<EvaluationState, BigDecimal> {

    private EvaluationState state;
    private int currentPosition = 0;
    private String expression;
    private Map<String, BigDecimal> valueMap = new HashMap<String, BigDecimal>();
    private Map<String, Operator<BigDecimal>> operatorMap = new HashMap<String, Operator<BigDecimal>>();
    private Deque<Operator<BigDecimal>> operatorStack = new LinkedList<Operator<BigDecimal>>();
    private List<String> outputQueue = new ArrayList<String>();
    private String valueBaseName = "value";
    private int valueCount = 0;
    private String errorMessage;

    public EvaluationContext(String expression) {
        this.expression = expression;
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

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
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
    public BigDecimal getResult() {
        return evaluateResult();
    }

    private BigDecimal evaluateResult() {
        if (!popOperatorStack()) {
            return null;
        }

        for (ListIterator<String> iterator = outputQueue.listIterator(); iterator.hasNext(); ) {
            String element = iterator.next();
            if (!element.startsWith(valueBaseName)) {
                iterator.remove();
                Operator<BigDecimal> operator = operatorMap.get(element);
                int argumentsCount = operator.getArgumentsCount();
                BigDecimal[] arguments = new BigDecimal[argumentsCount];
                for (int i = 0; i < argumentsCount; ++i) {
                    arguments[i] = valueMap.get(iterator.previous());
                    iterator.remove();
                }
                String valueName = valueBaseName + valueCount++;
                valueMap.put(valueName, operator.getResult(arguments));
                iterator.add(valueName);
            }
        }
        return valueMap.get(outputQueue.get(0));
    }

    private boolean popOperatorStack() {
        while (operatorStack.peek() != null) {
            if (operatorStack.peek().isLeftParenthesis()) {
                return false;
            }
            outputQueue.add(operatorStack.pop().getStringRepresentation());
        }
        return true;
    }

    /**
     * pushes number to a number stack
     *
     * @param number - value
     */
    public void pushNumber(BigDecimal number) {
        String valueName = valueBaseName + valueCount++;
        valueMap.put(valueName, number);
        outputQueue.add(valueName);
    }

    /**
     * tries to push an operator to a operator stack
     *
     * @param operator - operator to push
     * @return - returns false only if the operator is a closing parenthesis and it's mismatched
     */
    public boolean pushOperator(Operator<BigDecimal> operator) {
        if (!operatorMap.keySet().contains(operator.getStringRepresentation())) {
            operatorMap.put(operator.getStringRepresentation(), operator);
        }

        if (operator.isLeftParenthesis()) {
            operatorStack.push(operator);
        } else if (operator.isRightParenthesis()) {
            return pushRightParenthesis();
        } else {
            push(operator);
        }
        return true;
    }

    private boolean pushRightParenthesis() {
        Operator<BigDecimal> peekedOperator = operatorStack.peek();
        while (peekedOperator != null) {
            if (!peekedOperator.isLeftParenthesis()) {
                outputQueue.add(operatorStack.pop().getStringRepresentation());
            } else {
                operatorStack.pop();
                return true;
            }
            peekedOperator = operatorStack.peek();
        }
        return false;
    }

    private void push(Operator<BigDecimal> operator) {
        if (!operatorStack.isEmpty()) {
            Operator<BigDecimal> peekedOperator = operatorStack.peek();
            while (peekedOperator != null && !peekedOperator.isLeftParenthesis()
                    && (operator.getAssociativity() == Associativity.LEFT
                    && operator.getPrecedence() <= peekedOperator.getPrecedence()
                    || operator.getPrecedence() < peekedOperator.getPrecedence())
                    ) {

                outputQueue.add(operatorStack.pop().getStringRepresentation());
                peekedOperator = operatorStack.peek();
            }
        }
        operatorStack.push(operator);
    }
}
