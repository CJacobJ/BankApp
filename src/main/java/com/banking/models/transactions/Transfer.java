package com.banking.models.transactions;

import com.banking.models.TransactionList;

import java.time.LocalDateTime;

public class Transfer extends Transaction{

    private int toAccount;

    public Transfer(double newAmount, int newToAccount) {
        super(newAmount);
        toAccount = newToAccount;
    }

    public Transfer(int newTransactionID, double newAmount, LocalDateTime newDate, int newToAccount) {
        super(newTransactionID, newAmount, newDate);
        toAccount = newToAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    @Override
    public void sortSelf(TransactionList transList) {
        transList.addTransfer(this);
    }

    @Override
    public int typeNum() {
        return 2;
    }

    @Override
    public String toString() {
        return "TransferID: " + getTransactionID() + " Amount: " + getAmount() + " Date: " + getDate() + " Other Account: " + toAccount;
    }
}
