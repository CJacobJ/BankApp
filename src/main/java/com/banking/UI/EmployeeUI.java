package com.banking.UI;

import com.banking.models.Account;
import com.banking.models.Name;
import com.banking.models.User;
import com.banking.service.BankingService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EmployeeUI extends UserInterface {

    public EmployeeUI(BankingService newBankingService, Scanner newScanner) {
        bankingService = newBankingService;
        scanner = newScanner;
    }

    public void createUser() {
        User newUser = new User();
        String firstName;
        String lastName;
        String newPassword;
        int creditScore;

        System.out.println("Enter new username");
        newUser.setUsername(scanner.nextLine());

        System.out.println("Enter new password");
        newPassword = scanner.nextLine();

        System.out.println("Enter first name");
        firstName = scanner.nextLine();

        System.out.println("Enter last name");
        lastName = scanner.nextLine();

        System.out.println("Enter credit score");
        creditScore = getIntInput();

        newUser.setFullName(new Name(firstName, lastName));

        newUser.setCreationTime(LocalDateTime.now());
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setCreditScore(creditScore);

        bankingService.saveNewUser(newUser, newPassword);
    }

    public void reviewCredit() {

    }
}
