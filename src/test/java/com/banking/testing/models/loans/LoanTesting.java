package com.banking.testing.models.loans;

import com.banking.models.LoanList;
import com.banking.models.loans.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class LoanTesting {

    Loan testLoan;

    @Before
    public void init() {
        testLoan = new Loan(2000);
    }

    @Test
    public void ExcellentCreditTest() {      // Loan should automatically approve with low interest at good and excellent credit scores
        int testScore = 800;
        testLoan.autoApproval(testScore);
        assertTrue("Loan failed to properly approve.", testLoan.getApprovalStatus() instanceof ApprovedStatus);
        assertTrue("Loan interest rate is wrong.", testLoan.getRate() <= 0.05);
    }

    @Test
    public void goodCreditTest() {      // Loan should automatically approve with ok interest at good and excellent credit scores
        int testScore = 720;
        testLoan.autoApproval(testScore);
        assertTrue("Loan failed to properly approve.", testLoan.getApprovalStatus() instanceof ApprovedStatus);
        assertTrue("Loan interest rate is wrong.", testLoan.getRate() <= 0.10 && testLoan.getRate() >= 0.05);
    }

    @Test
    public void okCreditTest() {      // Loan should automatically request review with high interest at ok credit
        int testScore = 600;
        testLoan.autoApproval(testScore);
        assertTrue("Loan failed to properly set review.", testLoan.getApprovalStatus() instanceof ReviewStatus);
        assertTrue("Loan interest rate is wrong.", testLoan.getRate() >= 0.10);
    }

    @Test
    public void badCreditTest() {      // Loan should automatically deny at bad credit
        int testScore = 300;
        testLoan.autoApproval(testScore);
        assertTrue("Loan failed to properly deny.", testLoan.getApprovalStatus() instanceof DeniedStatus);
    }

    @Test(expected = IllegalStateException.class)
    public void testSort() {
        ApprovedStatus mockStatus = mock(ApprovedStatus.class);
        LoanList mockList = mock(LoanList.class);
        doThrow(new IllegalStateException("Received Loan and LoanList")).when(mockStatus).sortSelf(mockList, testLoan);

        testLoan.setApprovalStatus(mockStatus);

        testLoan.sortSelf(mockList);
    }

    @Test(expected = NullPointerException.class)
    public void testSortStatusNull() {
        ApprovedStatus mockStatus = mock(ApprovedStatus.class);
        LoanList mockList = mock(LoanList.class);
        doThrow(new IllegalStateException("Received Loan and LoanList")).when(mockStatus).sortSelf(mockList, testLoan);

        testLoan.sortSelf(mockList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortListNull() {
        ApprovedStatus mockStatus = mock(ApprovedStatus.class);
        LoanList mockList = null;
        doThrow(new IllegalStateException("Received Loan and LoanList")).when(mockStatus).sortSelf(mockList, testLoan);

        testLoan.setApprovalStatus(mockStatus);

        testLoan.sortSelf(mockList);
    }

}

