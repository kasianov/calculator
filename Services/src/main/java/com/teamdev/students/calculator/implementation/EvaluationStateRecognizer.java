package com.teamdev.students.calculator.implementation;

import com.teamdev.students.calculator.implementation.operations.*;
import com.teamdev.students.calculator.implementation.operations.factories.*;
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
        Map<String,OperationFactory<BigDecimal,MathematicalError>> operatorMap = new HashMap<String, OperationFactory<BigDecimal, MathematicalError>>();
        operatorMap.put(new BinaryMinusOperator().getStringRepresentation(), new BinaryMinusOperatorFactory());
        operatorMap.put(new PlusOperator().getStringRepresentation(), new PlusOperatorFactory());
        operatorMap.put(new MultiplyOperator().getStringRepresentation(), new MultiplyOperatorFactory());
        operatorMap.put(new DivideOperator().getStringRepresentation(), new DivideOperatorFactory());
        operatorMap.put(new PowerOperator().getStringRepresentation(), new PowerOperatorFactory());
        OperatorParser binaryOperatorParser = new OperatorParser(operatorMap);

        Map<String,OperationFactory<BigDecimal,MathematicalError>> functionMap = new HashMap<String, OperationFactory<BigDecimal, MathematicalError>>();
        functionMap.put(new SqrtFunction().getStringRepresentation(),new SqrtFunctionFactory());
        functionMap.put(new MinFunction().getStringRepresentation(),new MinFunctionFactory());
        functionMap.put(new MaxFunction().getStringRepresentation(),new MaxFunctionFactory());
        functionMap.put(new SumFunction().getStringRepresentation(),new SumFunctionFactory());
        functionMap.put(new SinFunction().getStringRepresentation(),new SinFunctionFactory());
        functionMap.put(new CosFunction().getStringRepresentation(),new CosFunctionFactory());
        functionMap.put(new PiFunction().getStringRepresentation(),new PiFunctionFactory());
        FunctionParser functionParser = new FunctionParser(functionMap);

        Map<EvaluationState, Parser<EvaluationContext>> evaluationStateParserMap = new HashMap<EvaluationState, Parser<EvaluationContext>>();
        evaluationStateParserMap.put(FINISH, new EndOfExpressionParser());
        evaluationStateParserMap.put(NUMBER, new DecimalParser());
        evaluationStateParserMap.put(ONLY_POSITIVE_NUMBER, new OnlyPositiveDecimalParser());
        evaluationStateParserMap.put(BINARY_OPERATOR, binaryOperatorParser);
        evaluationStateParserMap.put(UNARY_MINUS_OPERATOR, new UnaryMisusParser());
        evaluationStateParserMap.put(FUNCTION, functionParser);
        evaluationStateParserMap.put(FUNCTION_SEPARATOR, new FunctionSeparatorParser());
        evaluationStateParserMap.put(LEFT_PARENTHESIS, new LeftParenthesisParser());
        evaluationStateParserMap.put(LEFT_PARENTHESIS_AFTER_FUNCTION,new LeftParenthesisParser());
        evaluationStateParserMap.put(RIGHT_PARENTHESIS, new RightParenthesisParser());

        return new EvaluationStateRecognizer(evaluationStateParserMap);
    }

    @Override
    public boolean accept(EvaluationState currentState, EvaluationContext evaluationContext, boolean afterError) {
        Parser<EvaluationContext> parser = stateParserMap.get(currentState);
        return parser != null && parser.tryParse(evaluationContext, afterError);
    }
}
