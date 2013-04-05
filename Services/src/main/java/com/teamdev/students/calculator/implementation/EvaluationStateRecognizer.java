package com.teamdev.students.calculator.implementation;

import com.teamdev.students.calculator.implementation.operators.*;
import com.teamdev.students.calculator.implementation.parsers.*;
import com.teamdev.students.calculator.intefaces.Operator;
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
        List<Operator<BigDecimal>> operatorList = new ArrayList<Operator<BigDecimal>>();
        Collections.addAll(operatorList,
                new BinaryMinusOperator(),
                new PlusOperator(),
                new MultiplyOperator(),
                new DivideOperator(),
                new PowerOperator());
        OperatorParser binaryOperatorParser = new OperatorParser(operatorList);
        Map<EvaluationState, Parser<EvaluationContext>> evaluationStateParserMap = new HashMap<EvaluationState, Parser<EvaluationContext>>();
        evaluationStateParserMap.put(FINISH, new EndOfExpressionParser());
        //evaluationStateParserMap.put(NUMBER, new IntegerParser());
        evaluationStateParserMap.put(NUMBER, new DecimalParser());
        evaluationStateParserMap.put(BINARY_OPERATOR, binaryOperatorParser);
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
