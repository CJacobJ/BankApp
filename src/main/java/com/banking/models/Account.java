package com.banking.models;

/**
 * Holds all info related to an account.
 */
public class Account {
    private int accountID;
    private LoanList allLoans;
    private TransactionList allTransactions;

    public Account() {
        allLoans = new LoanList();
        allTransactions = new TransactionList();
    }

    public Account(int newID) {
        accountID = newID;
        allLoans = new LoanList();
        allTransactions = new TransactionList();
    }

    public int getAccountID() {
        return accountID;
    }

    public LoanList getAllLoans() {
        return allLoans;
    }

    public TransactionList getAllTransactions() {
        return allTransactions;
    }
}
