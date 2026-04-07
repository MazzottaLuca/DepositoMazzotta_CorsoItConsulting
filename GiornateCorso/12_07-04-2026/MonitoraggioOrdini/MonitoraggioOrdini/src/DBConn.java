import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn{
    private static DBConn instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/magazzino";
    private String user = "root";
    private String password = "";

    private DBConn() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Errore nella connessione al DB");
        }
    }

    public static DBConn getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConn();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}