package com.epam.cdp.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Calculator {

    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private static final char MINUS = '-';
    private static final char PLUS = '+';

    private static final Logger LOG = LoggerFactory.getLogger(Calculator.class);

    /**
     * Calculate expression
     *
     * @param expression the expression
     * @return result
     */
    public String calculate(String expression) {
        LinkedList<BigInteger> numbers = new LinkedList<>();
        LinkedList<Character> operations = new LinkedList<>();

        for (int i = 0; i < expression.length(); i++) {

            char sign = expression.charAt(i);
            if (isSpace(sign)) {
                continue;
            }
            if (sign == '(') {
                operations.add('(');
            } else if (sign == ')') {
                try {
                    while (operations.getLast() != '(') {
                        processOperator(numbers, operations.removeLast());
                    }
                    operations.removeLast();
                } catch (NoSuchElementException exception) {
                    LOG.error("Incorrect expression");
                }
            } else if (isOperator(sign)) {
                while (!operations.isEmpty() && priority(operations.getLast()) >= priority(sign)) {
                    processOperator(numbers, operations.removeLast());
                }
                operations.add(sign);
            } else {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number.append(expression.charAt(i++));
                }
                i--;
                numbers.add(new BigInteger(number.toString()));
            }
        }

        while (!operations.isEmpty()) {
            processOperator(numbers, operations.removeLast());
        }
        return numbers.get(0).toString();
    }

    /**
     * Check sign for white space
     *
     * @param sign the sign
     * @return result
     */
    private boolean isSpace(char sign) {
        return sign == ' ';
    }

    /**
     * Check operation
     *
     * @param operation the operation
     * @return result
     */
    private boolean isOperator(char operation) {
        return Arrays.asList(PLUS, '-', MULTIPLY, DIVIDE).contains(operation);
    }

    /**
     * Get priority for operation
     *
     * @param operation the operation
     * @return resulr
     */
    private int priority(char operation) {
        switch (operation) {
            case PLUS:
            case MINUS:
                return 1;
            case MULTIPLY:
            case DIVIDE:
                return 2;
            default:
                return -1;
        }
    }

    /**
     * Calculate numbers
     *
     * @param list      the list
     * @param operation the operation
     */
    private void processOperator(LinkedList<BigInteger> list, char operation) {
        BigInteger num1 = list.removeLast();
        BigInteger num2 = list.removeLast();

        switch (operation) {
            case PLUS:
                list.add(num2.add(num1));
                break;
            case MINUS:
                list.add(num2.subtract(num1));
                break;
            case MULTIPLY:
                list.add(num2.multiply(num1));
                break;
            case DIVIDE:
                list.add(num2.divide(num1));
                break;
            default:
                break;
        }
    }

}
