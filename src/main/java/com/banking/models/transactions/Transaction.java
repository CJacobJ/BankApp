package com.banking.models.transactions;

import com.banking.models.TransactionList;

import java.time.LocalDateTime;

/**
 * Base abstract class for all kinds of transactions
 */
public abstract class Transaction {
    private int transactionID;
    private double amount;
    private LocalDateTime date;

    public Transaction (double newAmount) {
        amount = newAmount;
        date = LocalDateTime.now();
    }

    public Transaction (int newTransactionID, double newAmount, LocalDateTime newDate) {
        transactionID = newTransactionID;
        amount = newAmount;
        date = newDate;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Inserts the Transaction into the correct list.
     * @param transList The TransactionList object to be added to.
     */
    public abstract void sortSelf(TransactionList transList);

    /**
     * Returns the type of transaction in int format.
     * @return Returns 0 for Withdrawal, 1 for Deposit, and 2 for Transfer.
     */
    public abstract int typeNum();

}
