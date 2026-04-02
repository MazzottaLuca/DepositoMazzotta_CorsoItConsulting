import java.sql.*;

public class DBConn {

    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "autostrada";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static final String BASE_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/";
    private static final String DB_URL = BASE_URL + DB_NAME + "?useSSL=false&serverTimezone=UTC";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static void inizializza() {
        System.out.println("Driver MySQL pronto.");
        creaDatabase();
        creaTabelle();
    }

    private static void creaDatabase() {
        String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME +
                " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
        try (Connection conn = DriverManager.getConnection(BASE_URL, USER, PASSWORD);
                Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Database '" + DB_NAME + "' creato");
        } catch (SQLException e) {
            System.err.println("Errore creazione database: " + e.getMessage());
        }
    }

    private static void creaTabelle() {
        String sql = """
                CREATE TABLE IF NOT EXISTS veicoli (
                    id              INT AUTO_INCREMENT PRIMARY KEY,
                    tipo            VARCHAR(20)  NOT NULL,
                    targa           VARCHAR(20)  NOT NULL UNIQUE,
                    velocita        DOUBLE       NOT NULL,
                    numero_assi     INT          NOT NULL,
                    attributo_extra VARCHAR(100),
                    pedaggio        DOUBLE,
                    creato_il       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
                )
                """;
        try (Connection conn = getConnection();
                Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Tabella 'veicoli' pronta.");
        } catch (SQLException e) {
            System.err.println("Errore creazione tabelle: " + e.getMessage());
        }
    }
}