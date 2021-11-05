package com.company.task7;

import com.company.task2.Child;

public class Main {
    public static void main(String[] args) {

        From from = new From(5, "hello", 15);
        To to = new To();

        BeanUtils.assign(to, from);

        System.out.println(to);

    }
}
