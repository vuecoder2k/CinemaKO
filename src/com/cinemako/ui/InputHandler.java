package com.cinemako.ui;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                if (scanner.hasNextInt()) {
                    int val = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    return val;
                } else {
                    System.out.println("Lỗi: Vui lòng nhập một số nguyên!");
                    scanner.nextLine(); // consume invalid input
                }
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi đọc dữ liệu. Vui lòng thử lại.");
                if (scanner.hasNextLine()) scanner.nextLine();
            }
        }
    }

    public String readLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                if (scanner.hasNextLine()) {
                    return scanner.nextLine().trim();
                }
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi đọc dữ liệu. Vui lòng thử lại.");
            }
        }
    }

    public boolean readConfirmation(String prompt) {
        while (true) {
            String input = readLine(prompt).toUpperCase();
            if (input.equals("Y")) return true;
            if (input.equals("N")) return false;
            System.out.println("Vui lòng chỉ nhập 'Y' hoặc 'N'.");
        }
    }

    public List<String> readSeats(String prompt) {
        String input = readLine(prompt);
        if (input.isEmpty()) return new ArrayList<>();
        return Arrays.asList(input.split("[,\\s]+"));
    }

    public void pause() {
        System.out.println("\nNhấn Enter để tiếp tục...");
        try {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        } catch (Exception e) {
            // Ignore
        }
    }
}
