package com.banking.models.loans;

import com.banking.models.LoanList;
import com.banking.models.loans.LoanStatus;

/**
 * Keeps track of the Loan's denied status.
 */
public class DeniedStatus implements LoanStatus {

    /**
     * Inserts newLoan into the denied list.
     * @param sortList The LoanList object containing the lists.
     * @param newLoan The Loan to be sorted.
     */
    @Override
    public void sortSelf(LoanList sortList, Loan newLoan) {
        sortList.addDenied(newLoan);
    }

    @Override
    public int statusNum() {
        return 2;
    }

    @Override
    public String statusString() {
        return "Denied";
    }
}
