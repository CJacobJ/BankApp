package com.banking.testing.database;

import com.banking.database.PropertiesConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PropertiesConnectorTester {

    PropertiesConnector testPropConn;

    @Before
    public void init() {
        testPropConn = new PropertiesConnector();
    }

    @Test
    public void testConnection() {
        try {
            Connection testConnection = testPropConn.newConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect", e);
        }
    }

}
