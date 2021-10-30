package com.company.task4;

import java.lang.reflect.Modifier;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAYS";
    public static final int SUM = 7;
    public static final boolean FLAG = true;



    public static void main(String[] args) {

        Class<?> exampleClass = Main.class;

        Arrays.stream(exampleClass.getDeclaredFields()).filter(field -> String.class.isAssignableFrom(field.getType()))
                .filter(field -> Modifier.isFinal(field.getModifiers()))
                .filter(field -> {
                    try {
                        field.setAccessible(true);
                        return field.getName().equals(field.get(exampleClass));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return false;
                }).forEach(field -> System.out.println(field.getName()));

    }
}
