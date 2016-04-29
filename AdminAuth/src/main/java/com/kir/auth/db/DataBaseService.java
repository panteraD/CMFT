package com.kir.auth.db;

import com.kir.auth.db.DBWork;

import java.sql.*;


public class DataBaseService {
    private Connection connection = DBWork.getConnection();
    private static final String DATE_INTO_DB = "insert into auth.dates(date_value) values (?)";
    private static final String GET_LAST_DATE = "SELECT date_value FROM auth.dates WHERE id = " +
                                                "(SELECT MAX(id) FROM auth.dates)";

    public void insertDate(String date) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DATE_INTO_DB)) {
            preparedStatement.setDate(1, Date.valueOf(date));

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getLastDate() {
        ResultSet resultSet = null;
        String date = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_DATE)) {
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                date = String.valueOf(resultSet.getDate(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return date;
    }
}
