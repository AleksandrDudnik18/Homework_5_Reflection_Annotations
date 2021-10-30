package com.company.task1;

import com.company.task6.Metric;

public interface Calculator {
    /**
     * Расчет факториала числа.
     *
     * @param number
     */
    @Metric
    int calc(int number);

}
