package com.ddworker.testClass;

public class calc {

    public static void main(String[] args) {
        int i = 1;
        while (i++ < Math.ceil(2015)) {
            if (2015 % i == 0) {
                System.out.println(i);
            }
        }
    }
}
