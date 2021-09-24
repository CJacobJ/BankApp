package com.banking.models.loans;

import com.banking.models.LoanList;

import java.time.LocalDateTime;

/**
 * Stores information on a Loan.
 */
public class Loan {

    private int loanID;
    private double amount;
    private double rate;
    private LocalDateTime dateApplied;
    private LoanStatus approvalStatus;

    @Override
    public String toString() {
        return "LoanID = " + loanID + " Amount Requested = " + amount + " Interest = " + rate + " Date Applied = " + dateApplied + " Approval Status: " + approvalStatus.statusString();
    }

    public Loan(int newLoanID, double newAmount, double newRate, LocalDateTime newDateApplied, LoanStatus newApprovalStatus) {
        loanID = newLoanID;
        amount = newAmount;
        rate = newRate;
        dateApplied = newDateApplied;
        approvalStatus = newApprovalStatus;
    }

    public Loan(double newAmount) {
        amount = newAmount;
        dateApplied = LocalDateTime.now();
    }

    /**
     * Sets the approvalStatus based on an input credit score.
     * @param creditScore The credit score of the user.
     */
    public void autoApproval(int creditScore) {
        if(creditScore >= 775) {
            approvalStatus = new ApprovedStatus();
            rate = 0.04;
        } else if(creditScore >= 675) {
            approvalStatus = new ApprovedStatus();
            rate = 0.08;
        } else if(creditScore >= 575) {
            approvalStatus = new ReviewStatus();
            rate = 0.12;
        } else {
            approvalStatus = new DeniedStatus();
            rate = 1;
        }
    }

    /**
     * Sorts the Loan into the correct list.
     * @param newList The collection of lists for the loan to sort itself into.
     */
    public void sortSelf(LoanList newList) {
        if (approvalStatus == null) {
            throw new NullPointerException("approvalStatus is null!");
        }
        if (newList == null) {
            throw new IllegalArgumentException("Passed LoanList is null!");
        }

        approvalStatus.sortSelf(newList, this);
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDateTime getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDateTime dateApplied) {
        this.dateApplied = dateApplied;
    }

    public LoanStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(LoanStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }


}
