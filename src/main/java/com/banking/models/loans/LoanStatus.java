package com.banking.models.loans;

import com.banking.models.LoanList;

/**
 * Keeps track of a Loan's status as approved, denied, or for review.
 */
public interface LoanStatus {

    /**
     * Inserts the Loan into the appropriate list.
     * @param sortList The LoanList object containing the lists.
     * @param newLoan The Loan to be sorted.
     */
    void sortSelf(LoanList sortList, Loan newLoan);

    /**
     * Returns the status in number format.
     * @return 0 for review, 1 for approved, 2 for denied.
     */
    int statusNum();

    /**
     * Returns status in string form
     * @return string of status
     */
    String statusString();
}
