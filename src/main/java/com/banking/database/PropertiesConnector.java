package com.banking.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropertiesConnector implements DatabaseConnector{

    private String username;
    private String password;
    private String url;

    private static Properties properties = new Properties();

    static {
        RegisterDriver();
    }

    /**
     * Registers a new driver for the database connector to use based on db.properties.
     */
    private static void RegisterDriver() {
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("db.properties"));

            Class driverClass = Class.forName(properties.getProperty("db.driver_name"));
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("db.properties not found", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't parse db.properties", e);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load jdbc driver class", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not create instance of jdbc driver class", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Driver constructor is marked as private", e);
        } catch (ClassCastException e) {
            throw new RuntimeException("Configured driver class is not a java.sql.Driver", e);
        }
    }

    public PropertiesConnector() {
        username = properties.getProperty("db.username");
        password = properties.getProperty("db.password");
        url = properties.getProperty("db.url");
    }

    @Override
    public Connection newConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
