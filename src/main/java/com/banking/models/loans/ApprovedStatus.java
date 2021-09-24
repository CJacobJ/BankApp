package com.banking.models.loans;

import com.banking.models.LoanList;
import com.banking.models.loans.LoanStatus;

/**
 * Keeps track of the Loan's approved status.
 */
public class ApprovedStatus implements LoanStatus {

    /**
     * Inserts the Loan into the approved list.
     * @param sortList The LoanList object containing the lists.
     * @param newLoan The Loan to be sorted.
     */
    @Override
    public void sortSelf(LoanList sortList, Loan newLoan) {
        sortList.addApproved(newLoan);
    }

    @Override
    public int statusNum() {
        return 1;
    }

    @Override
    public String statusString() {
        return "Approved";
    }
}
