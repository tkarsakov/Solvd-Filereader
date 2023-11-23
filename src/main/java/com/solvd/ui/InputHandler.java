package com.solvd.ui;

import java.util.Scanner;

public class InputHandler {
    public static String stringInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        // Try to scan something into the string until something is found
        while (input.isBlank()) {
            input = scanner.nextLine();
        }
        return input;
    }
}
