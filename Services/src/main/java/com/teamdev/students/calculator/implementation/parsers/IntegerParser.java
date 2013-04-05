package com.teamdev.students.calculator.implementation.parsers;

import com.teamdev.students.calculator.implementation.EvaluationContext;
import com.teamdev.students.calculator.intefaces.Parser;

import java.math.BigDecimal;

/**
 * parser of integers
 */
public class IntegerParser implements Parser<EvaluationContext> {


    @Override
    public boolean tryParse(EvaluationContext evaluationContext, boolean afterError) {
        String expression = evaluationContext.getExpression();
        int position = evaluationContext.getCurrentPosition();
        int count = 1;
        Integer tempNumber;
        Integer number = null;
        while (true) {
            if (position + count > expression.length()) {
                break;
            }
            try {
                tempNumber = Integer.parseInt(expression.substring(position, position + count));
                number = tempNumber;
                ++count;
            } catch (Exception ex) {
                if (count == 1 && expression.charAt(position) == '-') {
                    ++count;
                    continue;
                }
                break;
            }
        }
        --count;
        if (number != null) {
            if (!afterError) {
                evaluationContext.pushNumber(new BigDecimal(expression.substring(position, position + count)));
                evaluationContext.setCurrentPosition(position + count);
            } else {
                evaluationContext.setErrorMessage("Unexpected number token");
            }
            return true;
        }
        /*if (position >= expression.length()) {
            return false;
        }
        List<Character> digitCharacters = new ArrayList<Character>();
        Collections.addAll(digitCharacters, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        int count = 0;
        int tempPosition = position;
        while (true) {
            if (tempPosition >= expression.length()) {
                break;
            }
            Character character = expression.charAt(tempPosition);
            if (!digitCharacters.contains(character)
                    || count == 0 && character.equals('0')) {
                break;
            }
            ++count;
            ++tempPosition;
        }
        if (count > 0) {
            if (!afterError) {
                evaluationContext.pushNumber(new BigDecimal(expression.substring(position, position + count)));
                evaluationContext.setCurrentPosition(position + count);
            } else {
                evaluationContext.setErrorMessage("Unexpected number token");
            }
            return true;
        } */
        return false;
    }
}
