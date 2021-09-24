package com.banking.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds information on a single user
 */
public class User {
    private int UserID;
    private String Username;
    private Name FullName;
    private boolean IsEmployee;
    private LocalDateTime CreationTime;
    private LocalDateTime LastLogin;
    private List<Account> AccountList;
    private int CreditScore;

    public User() {
        AccountList = new ArrayList<Account>();
    }

    public User(int newUserID) {
        UserID = newUserID;
        AccountList = new ArrayList<Account>();
    }

    public User(int newUserID, String newUsername, boolean isEmployee, LocalDateTime newCreation, int newCreditScore) {
        UserID = newUserID;
        Username = newUsername;
        IsEmployee = isEmployee;
        CreationTime = newCreation;
        CreditScore = newCreditScore;
        AccountList = new ArrayList<Account>();
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int newUserID) {
        UserID = newUserID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Name getFullName() {
        return FullName;
    }

    public void setFullName(Name fullName) {
        FullName = fullName;
    }

    public boolean checkIsEmployee() {
        return IsEmployee;
    }

    public void setEmployee(boolean employee) {
        IsEmployee = employee;
    }

    public LocalDateTime getCreationTime() {
        return CreationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        CreationTime = creationTime;
    }

    public LocalDateTime getLastLogin() {
        return LastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        LastLogin = lastLogin;
    }

    public void addAccount(Account newAccount) {
        AccountList.add(newAccount);
    }

    public int getAccountNum() {
        return AccountList.size();
    }

    public Account getAccount(int accountNum) {
        return AccountList.get(accountNum);
    }

    public void setCreditScore(int newCreditScore) {
        CreditScore = newCreditScore;
    }

    public int getCreditScore() {
        return CreditScore;
    }
}
