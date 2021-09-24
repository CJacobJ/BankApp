package com.banking.models;

/**
 * Holds the name of a customer.
 */
public class Name {
    private String FirstName;
    private String LastName;

    public Name(String fName, String lName) {
        FirstName = fName;
        LastName = lName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }
}
