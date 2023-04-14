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

    /**
     * Creates a connection to the database
     * @return Connection object
     */
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

    /**
     * Executes a query and returns the result set
     * @param query  query to be executed
     * @return ResultSet object containing the result of the query
     * @throws Exception if query fails
     */
    public ResultSet executeQuery(String query) throws Exception {
        Connection connection = createConnection();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(query, e);
        }finally {
            closeConnection(connection);
        }

        return resultSet;
    }

    /**
     * Executes an update query
     * @param query query to be executed
     * @return Boolean if query is successful
     * @throws Exception if query fails
     */
    public boolean executeUpdate(String query) throws Exception {
        Connection connection = createConnection();
        boolean result = false;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(query, e);
        }finally {
            closeConnection(connection);
        }

        return result;
    }

    /**
     * Closes the connection to the database
     * @param connection Connection object
     */
    public void closeConnection(Connection connection) {
        try {
            System.out.println("Closing connection");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
