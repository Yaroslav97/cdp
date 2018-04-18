package com.epam.cdp.poliakov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DirtyReadTest {

    private static final String SELECT_ALL_FROM_STUDENTS = "SELECT * FROM students WHERE id < 100;";
    private static final String UPDATE_STUDENTS_FIRST_NAME = "UPDATE students SET first_name = 'New first_name' WHERE id < 100;";

    private static final String READ_DIRTY_DATA = "--------------------DIRTY DATA---------------------";
    private static final String READ_CLEAR_DATA = "--------------------CLEAR DATA---------------------";

    private Connection connection;

    @Before
    public void setUpDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cdp", "postgres", "root");
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
    }

    @Test
    public void dirtyReadTest() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(UPDATE_STUDENTS_FIRST_NAME);

        getAllStudentsName(connection, READ_DIRTY_DATA);
        connection.rollback();
        getAllStudentsName(connection, READ_CLEAR_DATA);

    }

    private void getAllStudentsName(Connection connection, String message) throws SQLException {
        Statement secondConnectionStatement = connection.createStatement();
        ResultSet resultSet = secondConnectionStatement.executeQuery(SELECT_ALL_FROM_STUDENTS);

        System.out.println(message);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name"));
        }
    }

    @After
    public void tearDown() throws SQLException {
        connection.close();
    }
}
