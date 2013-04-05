package com.teamdev.students.calculator.implementation;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class TestCalculator {
    private static Calculator calculator;

    @BeforeClass
    public static void initializeTest(){
        calculator = Calculator.createCalculator();
    }

    @Test
    public void testPlusOperator(){
        try{
            Assert.assertEquals("Unexpected result of operator 'plus'",new BigDecimal(20),calculator.evaluate("5+15"));
        } catch (EvaluationException ex){
            Assert.fail("Unexpected evaluation exception with operator 'plus'");
        }
    }

    @Test
    public void testMinusOperator(){
        try{
            Assert.assertEquals("Unexpected result of operator 'minus'",new BigDecimal(-5),calculator.evaluate("10-15"));
        } catch (EvaluationException ex){
            Assert.fail("Unexpected evaluation exception with operator 'minus'");
        }
    }

    @Test(expected = EvaluationException.class)
    public void testMisplacedOperatorPlus() throws EvaluationException{
        Assert.assertEquals("Misplaced operator 'plus' was not found",new BigDecimal(10),calculator.evaluate("5++5"));
        Assert.fail("Misplaced operator 'plus' was not found");
    }

    @Test(expected = EvaluationException.class)
    public void testMisplacedOperatorMinus() throws EvaluationException{
        Assert.assertEquals("Misplaced operator 'minus' was not found",new BigDecimal(10),calculator.evaluate("15-5-"));
        Assert.fail("Misplaced operator 'minus' was not found");
    }

    @Test(expected = EvaluationException.class)
    public void testUnexpectedEndOfExpression() throws EvaluationException{
        Assert.assertEquals(new BigDecimal(10),calculator.evaluate("10-"));
        Assert.fail("Unexpected end of expression was ignored");
    }

    @Test(expected = EvaluationException.class)
    public void testEmptyExpression() throws EvaluationException{
        Assert.assertEquals(new BigDecimal(10),calculator.evaluate(""));
        Assert.fail("Empty expression was ignored");
    }

    @Test
    public void testParentheses(){
        try{
            Assert.assertEquals("Unexpected result with parentheses",new BigDecimal(10),calculator.evaluate("5+(20-(5+10))"));
        } catch (EvaluationException ex){
            Assert.fail("Unexpected evaluation exception with parentheses");
        }
    }

    @Test(expected = EvaluationException.class)
    public void testMismatchedRightParenthesis()throws EvaluationException{
        Assert.assertEquals(new BigDecimal(10),calculator.evaluate("5+(6-1))"));
        Assert.fail("Mismatched right parenthesis was not found");
    }

    @Test(expected = EvaluationException.class)
    public void testMisplacedRightParenthesis()throws EvaluationException{
        Assert.assertEquals(new BigDecimal(10),calculator.evaluate("5)+(6-1)"));
        Assert.fail("Misplaced right parenthesis was not found");
    }

    @Test(expected = EvaluationException.class)
    public void testMisplacedLeftParenthesis()throws EvaluationException{
        Assert.assertEquals(new BigDecimal(10),calculator.evaluate("5(+(6-1)"));
        Assert.fail("Misplaced left parenthesis was not found");
    }

    @Test
    public void testOperatorMultiply(){
        try{
            Assert.assertEquals("Unexpected result of operator 'multiply'",new BigDecimal(20),calculator.evaluate("4*5"));
        } catch (EvaluationException ex){
            Assert.fail("Unexpected evaluation exception with operator 'multiply'");
        }
    }

    @Test
    public void testOperatorDivide(){
        try{
            Assert.assertEquals("Unexpected result of operator 'divide'",new BigDecimal(20),calculator.evaluate("80/4"));
        } catch (EvaluationException ex){
            Assert.fail("Unexpected evaluation exception with operator 'divide'");
        }
    }

    @Test
    public void testOperatorPower(){
        try{
            Assert.assertEquals("Unexpected result of operator 'power'",new BigDecimal(49),calculator.evaluate("7^"));
        } catch (EvaluationException ex){
            Assert.fail("Unexpected evaluation exception with operator 'power'");
        }
    }

    @Test
    public void testDecimal(){
        try{
            Assert.assertEquals("Unexpected result with decimal number",new BigDecimal(49),calculator.evaluate("4.5*2"));
        } catch (EvaluationException ex){
            Assert.fail("Unexpected evaluation exception with decimal number");
        }
    }

}
