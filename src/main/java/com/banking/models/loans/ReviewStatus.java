package com.banking.models.loans;

import com.banking.models.LoanList;
import com.banking.models.loans.LoanStatus;

/**
 * Keeps track of the Loan's under review status.
 */
public class ReviewStatus implements LoanStatus {

    /**
     * Sorts newLoan into the review list.
     * @param sortList The LoanList object containing the lists.
     * @param newLoan The Loan to be sorted.
     */
    @Override
    public void sortSelf(LoanList sortList, Loan newLoan) {
        sortList.addReview(newLoan);
    }

    @Override
    public int statusNum() {
        return 0;
    }

    @Override
    public String statusString() {
        return "Under Review";
    }


}
