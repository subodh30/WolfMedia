package edu.ncsu.dbms.wolfmedia.utilities;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Component
public class GenericDAO {

    private final String url = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/khrawool";
    private final String username = "khrawool";
    private final String password = "kartik_password";

    public Connection createConnection() {
        Connection connection = null;
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return connection;
    }
    public GenericDAO() {
    }

    public ResultSet executeQuery(String query) {
        Connection connection = createConnection();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }

        return resultSet;
    }

    public boolean executeUpdate(String query) {
        Connection connection = createConnection();
        boolean result = false;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }

        return result;
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
