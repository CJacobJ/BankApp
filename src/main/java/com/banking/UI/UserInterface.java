package com.banking.UI;

import com.banking.service.BankingService;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class UserInterface {

    protected Scanner scanner;
    protected BankingService bankingService;

    /**
     * Gets an int input from the user.
     * @return The int value from the user.
     */
    public int getIntInput() {
        int choice = -2;
        boolean inputSuccess = false;

        while (!inputSuccess) {
            try {
                choice = scanner.nextInt();
                inputSuccess = true;
            } catch (InputMismatchException e) {
                System.out.println("Please use an integer.");
            } finally {
                scanner.nextLine();
            }
        }
        return choice;
    }
}
