package com.banking.testing.models.loans;

import com.banking.models.loans.ApprovedStatus;
import com.banking.models.loans.Loan;
import com.banking.models.LoanList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ApprovedStatusTesting {

    ApprovedStatus testApproved;

    @Before
    public void init() {
        testApproved = new ApprovedStatus();
    }

    @Test
    public void testLoanStatusSorterReview() {
        LoanList mockLoanList = mock(LoanList.class);
        Loan mockLoan = mock(Loan.class);
        //when(mockLoanList.addReview(mockLoan).thenThrow(new IllegalStateException("Approved Loan added to review list")));

        doThrow(new IllegalStateException("Approved Loan added to review list")).when(mockLoanList).addReview(mockLoan);

        testApproved.sortSelf(mockLoanList, mockLoan);
    }

    @Test(expected = IllegalStateException.class)
    public void testLoanStatusSorterApproved() {
        LoanList mockLoanList = mock(LoanList.class);
        Loan mockLoan = mock(Loan.class);
        //when(mockLoanList.addApproved(mockLoan).thenThrow(new IllegalStateException("Approved Loan added to approved list")));

        doThrow(new IllegalStateException("Approved Loan added to approved list")).when(mockLoanList).addApproved(mockLoan);

        testApproved.sortSelf(mockLoanList, mockLoan);
    }

    @Test
    public void testLoanStatusSorterDenied() {
        LoanList mockLoanList = mock(LoanList.class);
        Loan mockLoan = mock(Loan.class);
        //when(mockLoanList.addDenied(mockLoan).thenThrow(new IllegalStateException("Approved Loan added to denied list")));

        doThrow(new IllegalStateException("Approved Loan added to denied list")).when(mockLoanList).addDenied(mockLoan);

        testApproved.sortSelf(mockLoanList, mockLoan);
    }

    @Test
    public void testLoanStatusNum() {
        Assert.assertEquals(testApproved.statusNum(), 1);
    }
}
