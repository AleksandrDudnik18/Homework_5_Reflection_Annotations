package com.company.task6;

import com.company.task1.Calculator;
import com.company.task5.CalculatorCache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PerformanceProxy implements InvocationHandler {

    private final Calculator calculator;
    private final CalculatorCache<Integer, Integer> calculatorCache;

    public PerformanceProxy(Calculator calculator) {
        this.calculator = calculator;
        this.calculatorCache = new CalculatorCache<>(10);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.isAnnotationPresent(Metric.class))
            return metricInvoke(method, args);
        return invoke(method, args);
    }

    private Object metricInvoke(Method method, Object[] args) {
        long startCount = System.nanoTime();
        Object result = invoke(method, args);
        long endCount = System.nanoTime();

        System.out.println("Time of calculating is: " + (endCount - startCount) + " nanoseconds");
        return result;
    }

    private Object invoke(Method method, Object[] args) {
        try {
            Integer result = calculatorCache.find((Integer) args[0]);
            if (result == null) {
                result = (Integer) method.invoke(calculator, args);
                calculatorCache.set((Integer) args[0], result);
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Evaluate error", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
