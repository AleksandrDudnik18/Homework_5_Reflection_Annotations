package com.company.task3;

import com.company.task2.Child;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Class<?> exampleClass = Child.class;

        do {

            Arrays.stream(exampleClass.getDeclaredMethods()).filter(el -> el.getName().startsWith("get"))
                    .forEach(el -> System.out.println(el.getName()));

            exampleClass = exampleClass.getSuperclass();

        } while (exampleClass.getSuperclass() != null);

    }
}
