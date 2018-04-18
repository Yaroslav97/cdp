import java.sql.*;

public class PostgresConnection {

    private final String URL = "jdbc:postgresql://localhost:5432/cdp";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "root";

    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgjireSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
        }

        Connection c = null;

        try {
            c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (c != null) {
            System.out.println("You made it, take control your database now!");
            return c;
        } else {
            System.out.println("Failed to make connection!");
            return null;
        }
    }

}
