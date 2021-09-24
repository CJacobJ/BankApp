package com.banking.service;

import com.banking.models.Account;
import com.banking.models.User;
import com.banking.models.loans.Loan;
import com.banking.models.transactions.Transaction;
import com.banking.models.transactions.Transfer;

public interface BankingService {

    /**
     * Loads a user.
     * @param userName The username of the user.
     * @param password The password of the user.
     * @return The found user.
     */
    public User loadUser(String userName, String password);

    /**
     * Saves a user.
     * @param newSave The user to be saved.
     */
    public void saveUser(User newSave);

    /**
     * Allows modification and approval of a loan.
     * @param modLoan The loan to be modified.
     */
    public void modifyLoan(Loan modLoan);

    /**
     * Saves a user with a new password.
     * @param newSave The user being saved.
     * @param password The new password.
     */
    public void saveNewUser(User newSave, String password);

    /**
     * Allows creation of a new account.
     * @param newSave The account's user
     */
    public int saveNewAccount(User newSave);

    /**
     * Allows saving a new non-transfer Transaction.
     * @param parent The account the Transaction was created by.
     * @param newTransaction The Transaction to be saved.
     */
    public void saveNewTransaction(Account parent, Transaction newTransaction);

    /**
     * Allows saving a new Transfer.
     * @param parent_id The account the Transfer was created by.
     * @param newTransfer The Transfer to be saved.
     */
    public void saveNewTransfer(int parent_id, Transfer newTransfer);

    /**
     * Allows saving a new Loan.
     * @param parent The Account the Loan was created by.
     * @param newLoan The Loan being saved.
     */
    public void saveNewLoan(Account parent, Loan newLoan);

    /**
     * Checks that an account exists.
     * @param accountNum The number of the account to be checked.
     * @return True if the account exists
     */
    public boolean checkAccount(int accountNum);
}
