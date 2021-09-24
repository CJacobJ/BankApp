package com.banking.service;

import com.banking.database.DatabaseConnector;
import com.banking.models.Account;
import com.banking.models.Name;
import com.banking.models.User;
import com.banking.models.loans.*;
import com.banking.models.transactions.Deposit;
import com.banking.models.transactions.Transaction;
import com.banking.models.transactions.Transfer;
import com.banking.models.transactions.Withdrawal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatabaseService implements BankingService{
    private DatabaseConnector connector;
    private Logger log = LogManager.getLogger(DatabaseService.class.getName());

    public DatabaseService(DatabaseConnector newConnector) {
        connector = newConnector;
    }

    @Override
    public User loadUser(String userName, String password) {
        log.debug("Starting loadUser - userName = " + userName + " password = " + password);
        String dataPassword;
        User newUser = null;
        int newID;
        boolean newStatus;
        LocalDateTime newCreated;
        int newCreditScore;

        try(Connection c = this.connector.newConnection()) {
            String sql = "select user_id, password, datecreated, is_employee, credit_score from users where username = ?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, userName);
            ResultSet results = prep.executeQuery();

            if (!results.next()) {
                log.warn("No user found by name: " + userName);
                throw new RuntimeException("No user by that name!");        // Todo - Add custom exception
            }

            dataPassword = results.getString("password");
            if (!BCrypt.checkpw(password, dataPassword)) {
                log.warn("Incorrect password");
                throw new RuntimeException("Incorrect password!");      // Todo - Add custom exception
            }

            newID = results.getInt("user_id");
            newStatus = results.getBoolean("is_employee");
            newCreated = results.getObject("datecreated", LocalDateTime.class);
            newCreditScore = results.getInt("credit_score");
            newUser = new User(newID, userName, newStatus, newCreated, newCreditScore);
            log.debug("User created");

            newUser.setFullName(loadName(newID, c));
            loadAccount(newID, newUser, c);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        log.debug("Returning user");
        return newUser;
    }

    private Name loadName(int userID, Connection c) throws SQLException {
        log.debug("Starting loadName");
        String firstName;
        String lastName;

        String sql = "select fname, lname from names where user_id = ?";
        PreparedStatement prep = c.prepareStatement(sql);
        prep.setInt(1, userID);
        ResultSet results = prep.executeQuery();

        if (!results.next()) {
            log.error("No Name found for userID: " + userID);
            throw new RuntimeException("No name for that user!");        // Todo - Add custom exception
        }

        firstName = results.getString("fname");
        lastName = results.getString("lname");

        log.debug("Ending loadName");
        return new Name(firstName, lastName);
    }


    private void loadAccount(int userID, User user, Connection c) throws SQLException {
        log.debug("Starting loadAccount");
        Account newAccount;

        String sql = "select account_id from accounts where user_id = ?";
        PreparedStatement prep = c.prepareStatement(sql);
        prep.setInt(1, userID);
        ResultSet results = prep.executeQuery();

        while (results.next()) {
            newAccount = new Account(results.getInt("account_id"));
            log.debug("Account created");

            loadTransactions(newAccount, c);
            loadLoans(newAccount, c);

            user.addAccount(newAccount);
        }
        log.debug("Ending loadAccount");
    }

    private void loadTransactions(Account account, Connection c) throws SQLException{
        log.debug("Starting loadTransactions");

        Transaction newTrans;
        int trans_id;
        double amount;
        LocalDateTime date_created;
        int trans_type;
        int sending_id;

        String sql = "select trans_id, amount, date_created, trans_type, sending_id from trans where account_id = ?";
        PreparedStatement prep = c.prepareStatement(sql);
        prep.setInt(1, account.getAccountID());
        ResultSet results = prep.executeQuery();

        while (results.next()) {
            trans_id = results.getInt("trans_id");
            amount = results.getDouble("amount");
            date_created = results.getObject("date_created", LocalDateTime.class);
            trans_type = results.getInt("trans_type");
            sending_id = results.getInt("sending_id");

            switch (trans_type) {
                case 0:
                    newTrans = new Withdrawal(trans_id, amount, date_created);
                    break;
                case 1:
                    newTrans = new Deposit(trans_id, amount, date_created);
                    break;
                case 2:
                    newTrans = new Transfer(trans_id, amount, date_created, sending_id);
                    break;
                default:
                    throw new IllegalStateException("Unexpected trans_type = " + trans_type);
            }
            
            newTrans.sortSelf(account.getAllTransactions());
        }
        log.debug("Leaving loadTransactions");
    }

    private void loadLoans(Account account, Connection c) throws SQLException {
        log.debug("Entering loadLoans");
        Loan newLoan;
        LoanStatus newLoanStatus;
        int loan_id;
        double amount;
        double interest;
        LocalDateTime date_applied;
        int status;

        String sql = "select loan_id, amount, interest, date_applied, status from loans where account_id = ?";
        PreparedStatement prep = c.prepareStatement(sql);
        prep.setInt(1, account.getAccountID());
        ResultSet results = prep.executeQuery();

        while (results.next()) {
            loan_id = results.getInt("loan_id");
            amount = results.getDouble("amount");
            interest = results.getDouble("interest");
            date_applied = results.getObject("date_applied", LocalDateTime.class);
            status = results.getInt("status");

            switch (status) {
                case 0:
                    newLoanStatus = new ReviewStatus();
                    break;
                case 1:
                    newLoanStatus = new ApprovedStatus();
                    break;
                case 2:
                    newLoanStatus = new DeniedStatus();
                    break;
                default:
                    throw new IllegalStateException("Unexpected status = " + status);
            }
            newLoan = new Loan(loan_id, amount, interest, date_applied, newLoanStatus);
            newLoan.sortSelf(account.getAllLoans());
        }
        log.debug("Leaving loadLoans");
    }

    @Override
    public void saveUser(User newSave) {
        try(Connection c = this.connector.newConnection()) {
            String sql = "update users set lastlogin = ? where user_id = ?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setObject(1, newSave.getLastLogin());
            prep.setInt(2, newSave.getUserID());
            prep.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void modifyLoan(Loan modLoan){

        try(Connection c = this.connector.newConnection()) {
            String sql = "update loans set amount = ?, interest = ?, status = ? where loan_id = ?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setDouble(1, modLoan.getAmount());
            prep.setDouble(2, modLoan.getRate());
            prep.setInt(3, modLoan.getApprovalStatus().statusNum());
            prep.setInt(4, modLoan.getLoanID());
            prep.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveNewUser(User newSave, String password) {
        String hashedPass;

        try(Connection c = this.connector.newConnection()) {
            hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(12));

            String sql = "insert into users (username, password, datecreated, lastlogin, is_employee, credit_score) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, newSave.getUsername());
            prep.setString(2, hashedPass);
            prep.setObject(3, newSave.getCreationTime());
            prep.setObject(4, newSave.getLastLogin());
            prep.setBoolean(5, newSave.checkIsEmployee());
            prep.setInt(6, newSave.getCreditScore());
            prep.executeUpdate();

            newSave.setUserID(getUserID(newSave.getUsername(), c));

            saveNewName(newSave, c);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private int getUserID(String username, Connection c) throws SQLException {
        String sql = "select user_id from users where username = ?";
        PreparedStatement prep = c.prepareStatement(sql);
        prep.setString(1, username);
        ResultSet results = prep.executeQuery();

        if (!results.next()) {
            log.error("No userID found for :" + username);
            throw new RuntimeException("No user_id found for that user!");        // Todo - Add custom exception
        }

        return results.getInt("user_id");
    }

    private void saveNewName(User newSave, Connection c) throws SQLException {
        Name saveName = newSave.getFullName();

        String sql = "insert into names (fname, lname, user_id) values (?, ?, ?)";
        PreparedStatement prep = c.prepareStatement(sql);
        prep.setString(1, saveName.getFirstName());
        prep.setString(2, saveName.getLastName());
        prep.setInt(3, newSave.getUserID());
        prep.executeUpdate();

    }

    @Override
    public int saveNewAccount(User newSave) {
        int check = -1;

        try(Connection c = this.connector.newConnection()) {
            String sql = "insert into accounts (user_id) values (?) returning account_id";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, newSave.getUserID());
            ResultSet results = prep.executeQuery();

            if (!results.next()) {
                log.error("Result error in saveNewAccount");
                throw new RuntimeException("No results in saveNewAccount!");        // Todo - Add custom exception
            }
            check = results.getInt(1);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return check;
    }

    @Override
    public void saveNewTransaction(Account parent, Transaction newTransaction) {

        try(Connection c = this.connector.newConnection()) {
            String sql = "insert into trans (amount, date_created, trans_type, account_id) values (?, ?, ?, ?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setDouble(1, newTransaction.getAmount());
            prep.setObject(2, newTransaction.getDate());
            prep.setInt(3, newTransaction.typeNum());
            prep.setInt(4, parent.getAccountID());
            prep.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveNewTransfer(int parent_id, Transfer newTransfer) {

        try(Connection c = this.connector.newConnection()) {

            String sql = "insert into trans (amount, date_created, trans_type, sending_id, account_id) values (?, ?, ?, ?, ?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setDouble(1, newTransfer.getAmount());
            prep.setObject(2, newTransfer.getDate());
            prep.setInt(3, newTransfer.typeNum());
            prep.setInt(4, newTransfer.getToAccount());
            prep.setInt(5, parent_id);
            prep.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveNewLoan(Account parent, Loan newLoan) {
        try(Connection c = this.connector.newConnection()) {
            String sql = "insert into loans (amount, interest, date_applied, status, account_id) values (?, ?, ?, ?, ?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setDouble(1, newLoan.getAmount());
            prep.setDouble(2, newLoan.getRate());
            prep.setObject(3, newLoan.getDateApplied());
            prep.setInt(4, newLoan.getApprovalStatus().statusNum());
            prep.setInt(5, parent.getAccountID());
            prep.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean checkAccount(int accountNum) {
        boolean check = false;

        try(Connection c = this.connector.newConnection()) {
            String sql = "select exists(select 1 from accounts where account_id = ?)";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setInt(1, accountNum);
            ResultSet results = prep.executeQuery();

            if (!results.next()) {
                log.error("Result error in checkAccount accountNum: " + accountNum);
                throw new RuntimeException("No results in checkAccount!");        // Todo - Add custom exception
            }
            check = results.getBoolean(1);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return check;
    }

    public ArrayList<Loan> getReviewLoans() {

        try(Connection c = this.connector.newConnection()) {
        String sql = "select l.amount, l.interest, l.date_applied, u.credit_score from public.loans l join public.accounts a inner join public.loans l on a.account_id = l.account_id join public.users u on a.user_id = u.user_id where l.status = 0";
        PreparedStatement prep = c.prepareStatement(sql);
        ResultSet results = prep.executeQuery();
        while (results.next()) {
            results.getDouble(1);
        }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
