import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_management?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        conn.setAutoCommit(true); // assicuriamoci che gli insert siano salvati subito
        return conn;
    }
}