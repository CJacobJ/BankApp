package com.banking.testing.models;

import com.banking.models.TransactionList;
import com.banking.models.transactions.Deposit;
import com.banking.models.transactions.Transfer;
import com.banking.models.transactions.Withdrawal;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class TransactionListTesting {

    TransactionList testingTransList;

    @Before
    public void init() {
        testingTransList = new TransactionList();
    }

    @Test
    public void addWithdrawalTest() {
        Withdrawal mockWithdrawal = mock(Withdrawal.class);

        testingTransList.addWithdrawal(mockWithdrawal);

        assertSame("mockWithdrawal not added to WithdrawalList!", mockWithdrawal, testingTransList.getWithdrawal(0));
    }

    @Test
    public void addDepositTest() {
        Deposit mockDeposit = mock(Deposit.class);

        testingTransList.addDeposit(mockDeposit);

        assertSame("mockDeposit not added to WithdrawalList!", mockDeposit, testingTransList.getDeposit(0));
    }

    @Test
    public void addTransferTest() {
        Transfer mockTransfer = mock(Transfer.class);

        testingTransList.addTransfer(mockTransfer);

        assertSame("mockTransfer not added to WithdrawalList!", mockTransfer, testingTransList.getTransfer(0));
    }
}
