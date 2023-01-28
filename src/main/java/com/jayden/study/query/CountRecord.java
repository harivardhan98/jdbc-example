package com.jayden.study.query;

import com.jayden.study.utils.JdbcUtils;

import java.sql.*;

/**
 * Count Record
 *
 * @author jayden-lee
 */
public class CountRecord {
    private static final String url = "jdbc:mysql://localhost:3306/employeesdata";
    private static final String user = "root";
    private static final String password = "hariRAO9698!";

    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = JdbcUtils.getConnection(url, user, password);

            int totalRows = getTotalRows();
            System.out.println("Total Rows : " + totalRows);

            int totalRows2 = getTotalRows2();
            System.out.println("Total Rows : " + totalRows2);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            JdbcUtils.closeConnection(connection);
        }
    }

    private static int getTotalRows() throws SQLException {
        String sql = "SELECT * FROM employeesdata.MANAGERS";

        int totalRows;

        try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                resultSet.last();
                totalRows = resultSet.getRow();
            } catch (SQLException e) {
                throw e;
            }
        }

        return totalRows;
    }

    private static int getTotalRows2() throws SQLException {
        String sql = "SELECT * FROM employeesdata.MANAGERS";

        int totalRows = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("MANAGER_ID"));
                System.out.println(resultSet.getString("FIRST_NAME"));
                System.out.println(resultSet.getString("LAST_NAME"));
                //totalRows = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }

        return totalRows;
    }
}
