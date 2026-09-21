package com.regsys.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Loads MySQL connection settings from db.properties (src/main/resources)
 * and hands out JDBC connections.
 *
 * Note on driver type: the whiteboard/coursework material refers to a
 * "Type 1" (JDBC-ODBC Bridge) driver. That driver class
 * (sun.jdbc.odbc.JdbcOdbcDriver) was removed from the JDK starting with
 * Java 8 and cannot run on any current machine. This class therefore uses
 * com.mysql.cj.jdbc.Driver, which is a Type 4 ("thin") driver - a pure-Java
 * driver that talks directly to MySQL over the network, no ODBC layer
 * involved. This is what real MySQL+Java projects use today.
 */
public class DBConnection {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try {
            Properties props = new Properties();
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
            if (input == null) {
                throw new RuntimeException("db.properties not found on classpath");
            }
            props.load(input);
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize DB configuration: " + e.getMessage(), e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
