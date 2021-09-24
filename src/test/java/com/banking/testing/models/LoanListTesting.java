package com.banking.testing.models;

import com.banking.models.LoanList;
import com.banking.models.loans.Loan;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LoanListTesting {

    LoanList testLoanList;

    @Before
    public void init() {
        testLoanList = new LoanList();
    }

    @Test
    public void addReviewTest() {
        Loan mockLoan = mock(Loan.class);

        testLoanList.addReview(mockLoan);

        assertSame(mockLoan, testLoanList.getReview(0));
    }

    @Test
    public void addApprovedTest() {
        Loan mockLoan = mock(Loan.class);

        testLoanList.addApproved(mockLoan);

        assertSame(mockLoan, testLoanList.getApproved(0));
    }

    @Test
    public void addDeniedTest() {
        Loan mockLoan = mock(Loan.class);

        testLoanList.addDenied(mockLoan);

        assertSame(mockLoan, testLoanList.getDenied(0));
    }
}
