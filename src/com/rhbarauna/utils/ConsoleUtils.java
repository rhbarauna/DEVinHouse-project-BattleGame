package com.rhbarauna.utils;

import java.util.Scanner;

public class ConsoleUtils {
    private static Scanner scanner;

    private ConsoleUtils() {}

    private static Scanner getScanner() {
        if(scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void print(final String message) {
        try {
            Thread.sleep(800);
            System.out.println(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int readInt(final String message) {
        print(message);
        Scanner in = getScanner();
        int input =  in.nextInt();
        in.nextLine();
        return input;
    }

    public static String readText(final String message) {
        print(message);
        Scanner in = getScanner();
        return in.nextLine();
    }
}
