package com.banking.service;

import com.banking.models.User;

import java.io.FileWriter;
import java.io.IOException;
/*
public class FileService implements BankingService{
    private final static String USER_FILENAME = "saves/users.bank";

    @Override
    public User loadUser(String userName, String password) {
        return null;
    }

    @Override
    public void saveUser(User newSave) {


    }

    @Override
    public void saveNewUser(User newSave, String password) {
        String userSerialized = newSave.getUserID() + ',' + newSave.getUsername() + ',' + newSave.getFullName().getFirstName()
                + ',' + newSave.getFullName().getLastName() + ',' + newSave.checkIsEmployee() + ',' + newSave.getCreationTime().toString()
                + ',' + newSave.getLastLogin().toString();
        FileWriter writer;

        try {
            writer = new FileWriter(USER_FILENAME, true);
            writer.write(userSerialized + "\n");
            writer.flush();

            } catch (IOException e) {
                throw new RuntimeException("Could not save ticket", e);
            }
    }

    @Override
    public boolean checkAccount(int accountNum) {
        return false;
    }
}*/
