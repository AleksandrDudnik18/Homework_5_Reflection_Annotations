package com.company.task2;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {

        Class<?> exampleClass = Child.class;
        
        do{

            for (Method method : exampleClass.getDeclaredMethods()) {
                System.out.println(method.getName());
            }
            exampleClass = exampleClass.getSuperclass();
        } while (exampleClass.getSuperclass() != null);

    }
}
