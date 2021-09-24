package com.banking.database;

import java.sql.Connection;
import java.sql.SQLException;


public interface DatabaseConnector {
    /**
     * Connects to a database and returns the connection.
     * @return Returns the completed connection.
     * @throws SQLException
     */
    Connection newConnection() throws SQLException;
}
