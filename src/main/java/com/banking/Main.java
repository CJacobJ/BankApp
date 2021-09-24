package com.banking;

import com.banking.UI.UserUI;
import com.banking.database.PropertiesConnector;
import com.banking.service.DatabaseService;

public class Main {

    public static void main(String[] args) {
        PropertiesConnector props = new PropertiesConnector();
        DatabaseService dataServe = new DatabaseService(props);
        UserUI ui = new UserUI(dataServe);      // TODO - switch out for dataServe
        ui.login();

    }
}
