package com.company.task5;

import com.company.task1.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        Calculator calculator = new CalculatorProxy();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String request;
            System.out.println("If you want to exit, write \"ex\".");
            System.out.print("Input value for calculated factorial: ");
            while (true) {
                if (reader.ready()) {
                    while (!(request = reader.readLine()).equals("ex")) {
                        System.out.println("Result is: " + calculator.calc(Integer.parseInt(request)));

                        System.out.println("If you want to exit, write \"ex\".");
                        System.out.print("Input value for calculated factorial: ");
                    }
                    System.out.println("See you later!");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
