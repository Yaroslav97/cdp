import entity.Result;
import entity.Student;
import entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class Main {

    private static final String INSERT_STUDENT = "INSERT INTO students(id, first_name, last_name, birthday, phone, primary_skill, created_datetime, updated_datetime) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_SUBJECT = "INSERT INTO subjects(id, subject_name, tutor)VALUES (?, ?, ?);";
    public static final String INSERT_RESULT = "INSERT INTO results(id, student_id, subject_id, mark) VALUES (?, ?, ?, ?);";

    public static void createStudent(Connection connection, Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {
            preparedStatement.setLong(1, student.getId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setTimestamp(4, student.getBirsday());
            preparedStatement.setString(5, student.getPhone());
            preparedStatement.setString(6, student.getSkill());
            preparedStatement.setTimestamp(7, student.getCreatedDatetime());
            preparedStatement.setTimestamp(8, student.getUpdatedDatetime());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createSubject(Connection connection, Subject subject) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECT)) {
            preparedStatement.setLong(1, subject.getId());
            preparedStatement.setString(2, subject.getSubjectName());
            preparedStatement.setString(3, subject.getTutor());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createResult(Connection connection, Result result) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESULT)) {
            preparedStatement.setLong(1, result.getId());
            preparedStatement.setLong(2, result.getStudentID());
            preparedStatement.setLong(3, result.getSubjectID());
            preparedStatement.setByte(4, result.getMark());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillStudents(Connection connection) {
        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());

        for (int i = 0; i < 100_000; i++) {
            Student student = new Student(i, "fist name" + i, "last name" + i, timestamp,
                    "099" + i, "Java", timestamp, timestamp);
            createStudent(connection, student);
        }
    }

    public static void fillSubjects(Connection connection) {
        for (int i = 0; i < 10; i++) {
            Subject subject = new Subject(i, "subject" + i, "tutor" + i);
            createSubject(connection, subject);
        }
    }

    public static void fillResults(Connection connection) {
        for (int i = 0; i < 100_000; i++) {

            Result result = new Result(i, i, i, (byte) new Random().nextInt(10));
            createResult(connection, result);
        }
    }

    public static void main(String[] args) {
        PostgresConnection postgresConnection = new PostgresConnection();
        Connection connection = postgresConnection.connect();

        fillStudents(connection);
        fillSubjects(connection);
        fillResults(connection);
    }
}
