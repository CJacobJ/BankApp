package com.banking.testing.models.transactions;

import com.banking.models.TransactionList;
import com.banking.models.transactions.Deposit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class DepositTesting {

    Deposit testDeposit;

    @Before
    public void init() {
        testDeposit = new Deposit(20.0);
    }

    @Test(expected = IllegalStateException.class)
    public void sortTransactionWithdrawal() {
        TransactionList mockTransList = mock(TransactionList.class);
        doThrow(new IllegalStateException("Deposit added to deposit list")).when(mockTransList).addDeposit(testDeposit);

        testDeposit.sortSelf(mockTransList);
    }

    @Test
    public void testTypeNum() {
        Assert.assertEquals(testDeposit.typeNum(), 1);
    }
}
