package com.banking.models.transactions;

import com.banking.models.TransactionList;

import java.time.LocalDateTime;

public class Deposit extends Transaction{

    public Deposit(double newAmount) {
        super(newAmount);
    }

    public Deposit(int newTransactionID, double newAmount, LocalDateTime newDate) {
        super(newTransactionID, newAmount, newDate);
    }

    @Override
    public void sortSelf(TransactionList transList) {
        transList.addDeposit(this);
    }

    @Override
    public int typeNum() {
        return 1;
    }

    @Override
    public String toString() {
        return "DepositID: " + getTransactionID() + " Amount: " + getAmount() + " Date: " + getDate();
    }
}
