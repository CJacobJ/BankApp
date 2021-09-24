package com.banking.testing.models.loans;

import com.banking.models.loans.Loan;
import com.banking.models.LoanList;
import com.banking.models.loans.ReviewStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ReviewStatusTesting {

    ReviewStatus testReview;

    @Before
    public void init() {
        testReview = new ReviewStatus();
    }

    @Test(expected = IllegalStateException.class)
    public void testLoanStatusSorterReview() {
        LoanList mockLoanList = mock(LoanList.class, RETURNS_SMART_NULLS);
        Loan mockLoan = mock(Loan.class, RETURNS_SMART_NULLS);
        //when(mockLoanList.addReview(any()).thenThrow(new IllegalStateException("Review Loan added to review list")));

        doThrow(new IllegalStateException("Review Loan added to review list")).when(mockLoanList).addReview(mockLoan);

        testReview.sortSelf(mockLoanList, mockLoan);
    }

    @Test
    public void testLoanStatusSorterApproved() {
        LoanList mockLoanList = mock(LoanList.class);
        Loan mockLoan = mock(Loan.class);
        //when(mockLoanList.addApproved(any()).thenThrow(new IllegalStateException("Review Loan added to approved list")));

        doThrow(new IllegalStateException("Review Loan added to approved list")).when(mockLoanList).addApproved(mockLoan);

        testReview.sortSelf(mockLoanList, mockLoan);
    }

    @Test
    public void testLoanStatusSorterDenied() {
        LoanList mockLoanList = mock(LoanList.class);
        Loan mockLoan = mock(Loan.class);
        //when(mockLoanList.addDenied(any()).thenThrow(new IllegalStateException("Review Loan added to denied list")));

        doThrow(new IllegalStateException("Review Loan added to denied list")).when(mockLoanList).addDenied(mockLoan);

        testReview.sortSelf(mockLoanList, mockLoan);
    }

    @Test
    public void testLoanStatusNum() {
        Assert.assertEquals(testReview.statusNum(), 0);
    }

}
