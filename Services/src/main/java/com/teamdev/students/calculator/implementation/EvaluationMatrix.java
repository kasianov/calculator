package com.teamdev.students.calculator.implementation;

import com.teamdev.students.calculator.intefaces.TransitionMatrix;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.teamdev.students.calculator.implementation.EvaluationState.*;

public class EvaluationMatrix implements TransitionMatrix<EvaluationState> {
    private EvaluationState startState;
    private EvaluationState finishState;
    private Map<EvaluationState, Set<EvaluationState>> possibleStatesMap;

    public EvaluationMatrix(EvaluationState finishState, Map<EvaluationState,
            Set<EvaluationState>> possibleStatesMap, EvaluationState startState) {
        this.finishState = finishState;
        this.possibleStatesMap = possibleStatesMap;
        this.startState = startState;
    }

    public static EvaluationMatrix createEvaluationMatrix() {
        Map<EvaluationState, Set<EvaluationState>> evaluationStateSetMap = new HashMap<EvaluationState, Set<EvaluationState>>();
        evaluationStateSetMap.put(START, EnumSet.of(NUMBER, LEFT_PARENTHESIS, FUNCTION));
        evaluationStateSetMap.put(NUMBER, EnumSet.of(BINARY_OPERATOR, FINISH, RIGHT_PARENTHESIS, FUNCTION_SEPARATOR));
        evaluationStateSetMap.put(BINARY_OPERATOR, EnumSet.of(NUMBER, LEFT_PARENTHESIS, FUNCTION));
        evaluationStateSetMap.put(FUNCTION, EnumSet.of(LEFT_PARENTHESIS_AFTER_FUNCTION));
        evaluationStateSetMap.put(FUNCTION_SEPARATOR, EnumSet.of(LEFT_PARENTHESIS, NUMBER, FUNCTION));
        evaluationStateSetMap.put(LEFT_PARENTHESIS, EnumSet.of(NUMBER, LEFT_PARENTHESIS, FUNCTION));
        evaluationStateSetMap.put(LEFT_PARENTHESIS_AFTER_FUNCTION, EnumSet.of(NUMBER, LEFT_PARENTHESIS, RIGHT_PARENTHESIS, FUNCTION));
        evaluationStateSetMap.put(RIGHT_PARENTHESIS, EnumSet.of(BINARY_OPERATOR, FINISH, RIGHT_PARENTHESIS, FUNCTION_SEPARATOR));

        return new EvaluationMatrix(FINISH, evaluationStateSetMap, START);
    }

    @Override
    public EvaluationState getStartState() {
        return startState;
    }

    @Override
    public boolean isFinishState(EvaluationState evaluationState) {
        return evaluationState == finishState;
    }

    @Override
    public Set<EvaluationState> getPossibleStates(EvaluationState currentState) {
        Set<EvaluationState> possibleStates = possibleStatesMap.get(currentState);
        return possibleStates != null ? possibleStates : EnumSet.noneOf(EvaluationState.class);
    }

    @Override
    public Set<EvaluationState> getAllStates() {
        return EnumSet.allOf(EvaluationState.class);
    }

}
