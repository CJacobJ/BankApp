package com.banking.models;

import com.banking.models.transactions.Deposit;
import com.banking.models.transactions.Transfer;
import com.banking.models.transactions.Withdrawal;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a list of all transactions by an account.
 */
public class TransactionList {

    private List<Withdrawal> WithdrawalList = new ArrayList<Withdrawal>();
    private List<Deposit> DepositList = new ArrayList<Deposit>();
    private List<Transfer> TransferList = new ArrayList<Transfer>();

    public void addWithdrawal(Withdrawal newWithdraw){
        WithdrawalList.add(newWithdraw);
    }

    public void addDeposit(Deposit newDeposit){
        DepositList.add(newDeposit);
    }

    public void addTransfer(Transfer newTransfer){
        TransferList.add(newTransfer);
    }

    public Withdrawal getWithdrawal(int withdrawalNum) {
        return WithdrawalList.get(withdrawalNum);
    }

    public Deposit getDeposit(int depositNum) {
        return DepositList.get(depositNum);
    }

    public Transfer getTransfer(int transferNum) {
        return TransferList.get(transferNum);
    }

    public int getWithdrawalLength() {
        return WithdrawalList.size();
    }

    public int getDepositLength() {
        return DepositList.size();
    }

    public int getTransferLength() {
        return TransferList.size();
    }

}
