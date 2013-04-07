package com.teamdev.students.calculator.implementation;

import com.teamdev.students.calculator.implementation.operations.*;
import com.teamdev.students.calculator.implementation.parsers.*;
import com.teamdev.students.calculator.intefaces.Operation;
import com.teamdev.students.calculator.intefaces.Parser;
import com.teamdev.students.calculator.intefaces.StateRecognizer;

import java.math.BigDecimal;
import java.util.*;

import static com.teamdev.students.calculator.implementation.EvaluationState.*;

public class EvaluationStateRecognizer implements StateRecognizer<EvaluationState, EvaluationContext> {
    private Map<EvaluationState, Parser<EvaluationContext>> stateParserMap;

    public EvaluationStateRecognizer(Map<EvaluationState, Parser<EvaluationContext>> stateParserMap) {
        this.stateParserMap = stateParserMap;
    }

    public static EvaluationStateRecognizer createEvaluationStateRecognizer() {
        List<Operation<BigDecimal, MathematicalError>> operatorList = new ArrayList<Operation<BigDecimal, MathematicalError>>();
        Collections.addAll(operatorList,
                new BinaryMinusOperator(),
                new PlusOperator(),
                new MultiplyOperator(),
                new DivideOperator(),
                new PowerOperator());
        OperatorParser binaryOperatorParser = new OperatorParser(operatorList);

        List<Operation<BigDecimal, MathematicalError>> functionList = new ArrayList<Operation<BigDecimal, MathematicalError>>();
        Collections.addAll(functionList,
                new SqrtFunction(),
                new MinFunction(),
                new MaxFunction(),
                new SumFunction(),
                new SinFunction(),
                new CosFunction());
        FunctionParser functionParser = new FunctionParser(functionList);

        Map<EvaluationState, Parser<EvaluationContext>> evaluationStateParserMap = new HashMap<EvaluationState, Parser<EvaluationContext>>();
        evaluationStateParserMap.put(FINISH, new EndOfExpressionParser());
        evaluationStateParserMap.put(NUMBER, new DecimalParser());
        evaluationStateParserMap.put(BINARY_OPERATOR, binaryOperatorParser);
        evaluationStateParserMap.put(FUNCTION, functionParser);
        evaluationStateParserMap.put(FUNCTION_SEPARATOR, new FunctionSeparatorParser());
        evaluationStateParserMap.put(LEFT_PARENTHESIS, new LeftParenthesisParser());
        evaluationStateParserMap.put(RIGHT_PARENTHESIS, new RightParenthesisParser());

        return new EvaluationStateRecognizer(evaluationStateParserMap);
    }

    @Override
    public boolean accept(EvaluationState currentState, EvaluationContext evaluationContext, boolean afterError) {
        Parser<EvaluationContext> parser = stateParserMap.get(currentState);
        return parser != null && parser.tryParse(evaluationContext, afterError);
    }
}
