package com.banking.testing.models.loans;

import com.banking.models.loans.DeniedStatus;
import com.banking.models.loans.Loan;
import com.banking.models.LoanList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class DeniedStatusTesting {

    DeniedStatus testDenied;

    @Before
    public void init() {
        testDenied = new DeniedStatus();
    }

    @Test
    public void testLoanStatusSorterReview() {
        LoanList mockLoanList = mock(LoanList.class);
        Loan mockLoan = mock(Loan.class);
        //when(mockLoanList).addReview(mockLoan).thenThrow(new IllegalStateException("Denied Loan added to review list"));

        doThrow(new IllegalStateException("Denied Loan added to review list")).when(mockLoanList).addReview(mockLoan);

        testDenied.sortSelf(mockLoanList, mockLoan);
    }

    @Test
    public void testLoanStatusSorterApproved() {
        LoanList mockLoanList = mock(LoanList.class);
        Loan mockLoan = mock(Loan.class);
        //when(mockLoanList.addApproved(mockLoan).thenThrow(new IllegalStateException("Denied Loan added to approved list")));

        doThrow(new IllegalStateException("Denied Loan added to approved list")).when(mockLoanList).addApproved(mockLoan);

        testDenied.sortSelf(mockLoanList, mockLoan);
    }

    @Test(expected = IllegalStateException.class)
    public void testLoanStatusSorterDenied() {
        LoanList mockLoanList = mock(LoanList.class);
        Loan mockLoan = mock(Loan.class);
        //when(mockLoanList.addDenied(mockLoan).thenThrow(new IllegalStateException("Denied Loan added to denied list")));

        doThrow(new IllegalStateException("Denied Loan added to denied list")).when(mockLoanList).addDenied(mockLoan);

        testDenied.sortSelf(mockLoanList, mockLoan);
    }

    @Test
    public void testLoanStatusNum() {
        Assert.assertEquals(testDenied.statusNum(), 2);
    }

}
