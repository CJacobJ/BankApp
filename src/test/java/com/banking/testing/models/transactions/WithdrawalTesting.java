package com.banking.testing.models.transactions;

import com.banking.models.TransactionList;
import com.banking.models.transactions.Withdrawal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class WithdrawalTesting {

    Withdrawal testWithdrawal;

    @Before
    public void init() {
        testWithdrawal = new Withdrawal(20.0);
    }

    @Test(expected = IllegalStateException.class)
    public void sortTransactionWithdrawal() {
        TransactionList mockTransList = mock(TransactionList.class);
        doThrow(new IllegalStateException("Withdrawal added to withdrawal list")).when(mockTransList).addWithdrawal(testWithdrawal);

        testWithdrawal.sortSelf(mockTransList);
    }

    @Test
    public void testTypeNum() {
        Assert.assertEquals(testWithdrawal.typeNum(), 0);
    }

}
