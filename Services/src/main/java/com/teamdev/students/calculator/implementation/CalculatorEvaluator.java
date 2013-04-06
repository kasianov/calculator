package com.teamdev.students.calculator.implementation;

import com.teamdev.students.calculator.intefaces.Evaluator;
import com.teamdev.students.calculator.intefaces.Operation;

import java.math.BigDecimal;
import java.util.*;

public class CalculatorEvaluator implements Evaluator<BigDecimal, Operation<BigDecimal>> {

    private Deque<FunctionStackElement> functionStack = new LinkedList<FunctionStackElement>();
    private Map<String, BigDecimal> valueMap = new HashMap<String, BigDecimal>();
    private Deque<String> operationStack = new LinkedList<String>();
    private Map<String, Operation<BigDecimal>> operationMap = new HashMap<String, Operation<BigDecimal>>();
    private List<String> outputQueue = new LinkedList<String>();
    private String operatorBaseName = "operator";
    private int operationCount;
    private String functionBaseName = "function";
    private String valueBaseName = "value";
    private int valueCount;
    private String leftParenthesisName = "leftParenthesis";

    private BigDecimal evaluateResult() {
        if (!popOutOperationStack()) {
            return null;
        }
        for (ListIterator<String> iterator = outputQueue.listIterator(); iterator.hasNext(); ) {
            String element = iterator.next();
            if (!element.startsWith(valueBaseName)) {
                iterator.remove();
                Operation<BigDecimal> operation = operationMap.get(element);
                int argumentsCount = operation.getArgumentsCount();
                BigDecimal[] arguments = new BigDecimal[argumentsCount];
                for (int i = 0; i < argumentsCount; ++i) {
                    arguments[i] = valueMap.get(iterator.previous());
                    iterator.remove();
                }
                String valueName = valueBaseName + valueCount++;
                valueMap.put(valueName, operation.getResult(arguments));
                iterator.add(valueName);
            }
        }
        return valueMap.get(outputQueue.get(0));
    }

    private boolean popOutOperationStack() {
        while (operationStack.peek() != null) {
            if (operationStack.peek().equals(leftParenthesisName)) {
                return false;
            }
            outputQueue.add(operationStack.pop());
        }
        return true;
    }

    @Override
    public boolean pushValue(BigDecimal value) {
        String valueName = valueBaseName + valueCount++;
        valueMap.put(valueName, value);
        outputQueue.add(valueName);
        FunctionStackElement lastFunction = functionStack.peek();
        if (lastFunction != null && lastFunction.getAddedArgumentsCount() == 0) {
            return lastFunction.incrementArgumentsCount();
        }
        return true;
    }

    @Override
    public void pushOperator(Operation<BigDecimal> operator) {
        String name = operatorBaseName + operationCount++;
        operationMap.put(name, operator);
        if (!operationStack.isEmpty()) {
            Operation<BigDecimal> peekedOperator = operationMap.get(operationStack.peek());
            while (peekedOperator != null
                    && (operator.getAssociativity() == Associativity.LEFT
                    && operator.getPrecedence() <= peekedOperator.getPrecedence()
                    || operator.getPrecedence() < peekedOperator.getPrecedence())
                    ) {

                outputQueue.add(operationStack.pop());
                peekedOperator = operationMap.get(operationStack.peek());
            }
        }
        operationStack.push(name);
    }

    @Override
    public void pushLeftParenthesis() {
        operationStack.push(leftParenthesisName);
    }

    @Override
    public boolean pushRightParenthesis() {
        String operator = operationStack.peek();
        while (operator != null) {
            if (!operator.startsWith(leftParenthesisName)) {
                operationStack.pop();
                outputQueue.add(operator);
            } else {
                operationStack.pop();
                operator = operationStack.peek();
                if (operator != null && operator.startsWith(functionBaseName)) {
                    operationStack.pop();
                    outputQueue.add(operator);
                    FunctionStackElement lastFunction = functionStack.pop();
                    return lastFunction.hasMinimumArgumentsCount();
                }
                return true;
            }
            operator = operationStack.peek();
        }
        return false;
    }

    @Override
    public boolean pushFunction(Operation<BigDecimal> operator) {
        String name = functionBaseName + operationCount++;
        operationMap.put(name, operator);
        FunctionStackElement lastFunction = functionStack.peek();
        functionStack.push(new FunctionStackElement(operator));
        operationStack.push(name);
        if (lastFunction != null && lastFunction.getAddedArgumentsCount() == 0) {
            return lastFunction.incrementArgumentsCount();
        }
        return true;
    }

    @Override
    public boolean pushFunctionSeparator() {
        String operator = operationStack.peek();
        while (operator != null) {
            if (!operator.startsWith(leftParenthesisName)) {
                operationStack.pop();
                outputQueue.add(operator);
            } else {
                FunctionStackElement lastFunction = functionStack.peek();
                return lastFunction.incrementArgumentsCount();
            }
            operator = operationStack.peek();
        }
        return false;
    }

    @Override
    public BigDecimal getResult() {
        return evaluateResult();
    }

    private class FunctionStackElement {
        private int addedArgumentsCount;
        private Operation<BigDecimal> function;

        public FunctionStackElement(Operation<BigDecimal> function) {
            this.function = function;
        }

        public int getAddedArgumentsCount() {
            return addedArgumentsCount;
        }

        public boolean incrementArgumentsCount() {
            ++addedArgumentsCount;
            function.setArgumentsCount(addedArgumentsCount);
            return addedArgumentsCount <= function.getMaximumArgumentsCount();
        }

        public boolean hasMinimumArgumentsCount() {
            return addedArgumentsCount >= function.getMinimumArgumentsCount();
        }
    }
}
