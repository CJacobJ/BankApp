package com.banking.UI;

import com.banking.models.Account;
import com.banking.models.User;
import com.banking.service.BankingService;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Handles querying the user and getting input.
 */
public class UserUI extends UserInterface{


    public UserUI(BankingService newBankingService) {
        bankingService = newBankingService;
        scanner = new Scanner(System.in);
    }

    /**
     * Allows the user to log in.
     */
    public void login() {
        String username;
        String password;
        User user;

        System.out.println("Enter your username");
        username = scanner.nextLine();
        System.out.println("Enter your password.");
        password = scanner.nextLine();

        user = bankingService.loadUser(username, password);
        user.setLastLogin(LocalDateTime.now());

        System.out.println("Welcome " + user.getFullName().getFirstName() + " " + user.getFullName().getLastName());

        if(user.checkIsEmployee()) {
            EmployeeUI employeeUI = new EmployeeUI(bankingService, scanner);
            employeeUI.createUser();
        } else {
            userUI(user);

            bankingService.saveUser(user);
        }
    }

    /**
     * Gives a user the ability to access or create new accounts.
     * @param user The logged in user.
     */
    private void userUI(User user) {
        char choice;
        boolean exit = false;

        while(!exit) {
            System.out.println("You have " + user.getAccountNum() + " accounts.");
            System.out.println("Enter A to access an account, or B to create a new one. Enter E to exit");
            choice = scanner.next().charAt(0);
            scanner.nextLine();

            switch(choice) {
                case 'A':
                    userPick(user);
                    break;
                case 'B':
                    int accountNum = bankingService.saveNewAccount(user);
                    Account newAccount = new Account(accountNum);
                    user.addAccount(newAccount);

                    break;
                case 'E':
                    exit = true;
                    break;
            }
        }
    }

    /**
     * Allows the user to access their accounts.
     * @param user The logged in user.
     */
    private void userPick(User user) {
        boolean exit = false;
        int choice = -1;

        while(!exit) {
            System.out.println("Enter the desired account number, or -1 to exit.");

            choice = getIntInput();

            if (choice > -1) {
                try {
                    AccountUI accountUI = new AccountUI(user.getAccount(choice - 1), bankingService, scanner, user);
                    accountUI.accountActions();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please choose an account.");
                }
            } else {
                exit = true;
            }
        }
    }


}
