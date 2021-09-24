package com.banking.models.transactions;

import com.banking.models.TransactionList;

import java.time.LocalDateTime;

public class Withdrawal extends Transaction{


    public Withdrawal(double newAmount) {
        super(newAmount);
    }

    public Withdrawal(int newTransactionID, double newAmount, LocalDateTime newDate) {
        super(newTransactionID, newAmount, newDate);
    }

    @Override
    public void sortSelf(TransactionList transList) {
        transList.addWithdrawal(this);
    }

    @Override
    public int typeNum() {
        return 0;
    }

    @Override
    public String toString() {
        return "WithdrawalID: " + getTransactionID() + " Amount: " + getAmount() + " Date: " + getDate();
    }
}
