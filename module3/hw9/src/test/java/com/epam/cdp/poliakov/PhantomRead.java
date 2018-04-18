package com.epam.cdp.poliakov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PhantomRead {

    private static final String SELECT_STUDENTS_WHERE_NAME_CONTAINS_345 = "SELECT * FROM students WHERE first_name LIKE '%345'";
    private static final String UPDATE_STUDENTS = "UPDATE students SET first_name = 'NULL' WHERE first_name = 'fist_name345'";

    private static final String READ_PHANTOM_DATA = "-----------------Read phantom data-----------------";
    private static final String READ_WITHOUT_PHANTOM_DATA = "-----------------Read data without of any phantom-----------------";

    private Connection connection;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cdp", "postgres", "root");
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
    }

    @Test
    public void phantomReadTest() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(UPDATE_STUDENTS);

        getStudentNames(connection, READ_PHANTOM_DATA);
        connection.rollback();
        getStudentNames(connection, READ_WITHOUT_PHANTOM_DATA);

    }

    private void getStudentNames(Connection connection, String message) throws SQLException {
        Statement secondConnectionStatement = connection.createStatement();
        ResultSet resultSet = secondConnectionStatement.executeQuery(SELECT_STUDENTS_WHERE_NAME_CONTAINS_345);
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
