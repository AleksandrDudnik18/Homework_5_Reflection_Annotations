package com.company.task5;

import com.company.task1.Calculator;
import com.company.task1.CalculatorImpl;

public class CalculatorProxy implements Calculator {

    private final CalculatorImpl calculator;
    private final CalculatorCache<Integer, Integer> calculatorCache;

    public CalculatorProxy() {
        this.calculator = new CalculatorImpl();
        this.calculatorCache = new CalculatorCache<>(10);
    }

    @Override
    public int calc(int number) {
        Integer value = calculatorCache.find(number);
        if (value == null) {
            value = calculator.calc(number);
            calculatorCache.set(number, value);
        }
        return value;
    }

}
