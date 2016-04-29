package com.kir.auth.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Kirill Zhitelev on 04.02.2016.
 */
public class DBWork {

    //Change to your prefs
    private static final String URL = "jdbc:postgresql://localhost:5432/auth";
    private static final String LOGIN = "postgres";
    private static final String PASS = "admin";

    private static Connection connection;

    private DBWork() {

    }

    public static Connection getConnection() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
