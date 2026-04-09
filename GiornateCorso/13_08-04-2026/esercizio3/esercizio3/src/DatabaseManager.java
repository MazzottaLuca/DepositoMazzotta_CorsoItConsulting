import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connectServer() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Connection connectDB() throws SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/bar_db?serverTimezone=UTC";
        return DriverManager.getConnection(dbUrl, USER, PASSWORD);
    }
}