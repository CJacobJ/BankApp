package com.banking.models;

import com.banking.models.loans.Loan;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds all loans for an account.
 */
public class LoanList {

    public List<Loan> ReviewList = new ArrayList<Loan>();
    public List<Loan> ApprovedList = new ArrayList<Loan>();
    public List<Loan> DeniedList = new ArrayList<Loan>();


    public Loan getReview(int loanNum) {
        return ReviewList.get(loanNum);
    }

    public Loan getApproved(int loanNum) {
        return ApprovedList.get(loanNum);
    }

    public Loan getDenied(int loanNum) {
        return DeniedList.get(loanNum);
    }

    public void addReview(Loan newLoan) {
        ReviewList.add(newLoan);
    }

    public void addApproved(Loan newLoan) {
        ApprovedList.add(newLoan);
    }

    public void addDenied(Loan newLoan) {
        DeniedList.add(newLoan);
    }

    public int getReviewLength() {
        return ReviewList.size();
    }

    public int getApprovedLength() {
        return ApprovedList.size();
    }

    public int getDeniedLength() {
        return DeniedList.size();
    }
}
