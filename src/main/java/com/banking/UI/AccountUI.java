package com.banking.UI;

import com.banking.models.Account;
import com.banking.models.User;
import com.banking.models.loans.Loan;
import com.banking.models.transactions.Deposit;
import com.banking.models.transactions.Transaction;
import com.banking.models.transactions.Transfer;
import com.banking.models.transactions.Withdrawal;
import com.banking.service.BankingService;

import java.util.Scanner;

/**
 * Handles UI and user input relating to individual accounts.
 */
public class AccountUI extends UserInterface{

    private Account account;
    private User user;

    public AccountUI(Account newAccount, BankingService newBankingService, Scanner newScanner, User newUser)
    {
        scanner = newScanner;
        account = newAccount;
        bankingService = newBankingService;
        user = newUser;
    }

    public void accountActions() {
        boolean exit = false;
        char choice;

        System.out.println("Account number: " + account.getAccountID());

        while(!exit) {
            System.out.println("Enter A to withdraw");
            System.out.println("Enter B to deposit");
            System.out.println("Enter C to transfer funds");
            System.out.println("Enter D to apply for credit");
            System.out.println("Enter E to view transaction history");
            System.out.println("Enter F to view credit applications");
            System.out.println("Enter X to exit");

            choice = scanner.next().charAt(0);
            scanner.nextLine();

            switch(choice) {
                case 'A':
                    withdraw();
                    break;
                case 'B':
                    deposit();
                    break;
                case 'C':
                    transfer();
                    break;
                case 'D':
                    applyCredit();
                    break;
                case 'E':
                    viewTransactions();
                    break;
                case 'F':
                    viewLoans();
                    break;
                case 'X':
                    exit = true;
                    break;
            }
        }
    }

    private void withdraw() {
        Withdrawal newWithdrawal;
        int choice;

        System.out.println("Enter amount to withdraw.");

        choice = getIntInput();

        newWithdrawal = new Withdrawal(choice);
        newWithdrawal.sortSelf(account.getAllTransactions());
        bankingService.saveNewTransaction(account, newWithdrawal);
    }

    private void deposit() {
        Deposit newDeposit;
        int choice;

        System.out.println("Enter amount to deposit.");

        choice = getIntInput();

        newDeposit = new Deposit(choice);
        newDeposit.sortSelf(account.getAllTransactions());
        bankingService.saveNewTransaction(account, newDeposit);
    }

    private void transfer() {
        Transfer newTransfer;
        Transfer pairTransfer;
        int transferAmount;
        int choice;

        System.out.println("Enter the other account's number.");
        choice = getIntInput();

        if(bankingService.checkAccount(choice)) {
            System.out.println("Enter the amount to transfer.");
            transferAmount = getIntInput();

            newTransfer = new Transfer(-(transferAmount), choice);
            pairTransfer = new Transfer(transferAmount, account.getAccountID());
            newTransfer.sortSelf(account.getAllTransactions());
            bankingService.saveNewTransfer(account.getAccountID(), newTransfer);
            bankingService.saveNewTransfer(choice, pairTransfer);
        }
    }

    private void applyCredit() {
        Loan newLoan;
        int amount;

        System.out.println("Enter the size of the loan.");
        amount = getIntInput();

        newLoan = new Loan(amount);
        newLoan.autoApproval(user.getCreditScore());
        newLoan.sortSelf(account.getAllLoans());
        bankingService.saveNewLoan(account, newLoan);
    }

    private void viewTransactions() {
        for (int i = 0; i < account.getAllTransactions().getWithdrawalLength(); i++) {
            System.out.println(account.getAllTransactions().getWithdrawal(i));
        }
        System.out.println("");
        for (int i = 0; i < account.getAllTransactions().getDepositLength(); i++) {
            System.out.println(account.getAllTransactions().getDeposit(i));
        }
        System.out.println("");
        for (int i = 0; i < account.getAllTransactions().getTransferLength(); i++) {
            System.out.println(account.getAllTransactions().getTransfer(i));
        }
        System.out.println("");
    }

    private void viewLoans() {
        for (int i = 0; i < account.getAllLoans().getReviewLength(); i++) {
            System.out.println(account.getAllLoans().getReview(i));
        }
        System.out.println(" ");
        for (int i = 0; i < account.getAllLoans().getApprovedLength(); i++) {
            System.out.println(account.getAllLoans().getApproved(i));
        }
        System.out.println(" ");
        for (int i = 0; i < account.getAllLoans().getDeniedLength(); i++) {
            System.out.println(account.getAllLoans().getDenied(i));
        }
        System.out.println(" ");
    }
}
