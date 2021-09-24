package com.banking.testing.models.transactions;

import com.banking.models.TransactionList;
import com.banking.models.transactions.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class TransferTesting {

    Transfer testTransfer;

    @Before
    public void init() {
        testTransfer = new Transfer(20.0, 0);
    }

    @Test(expected = IllegalStateException.class)
    public void sortTransactionWithdrawal() {
        TransactionList mockTransList = mock(TransactionList.class);
        doThrow(new IllegalStateException("Transfer added to transfer list")).when(mockTransList).addTransfer(testTransfer);

        testTransfer.sortSelf(mockTransList);
    }

    @Test
    public void testTypeNum() {
        Assert.assertEquals(testTransfer.typeNum(), 2);
    }
}
