import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {

    private static final String URL_SERVER = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "hamburgeria";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL_SERVER + DATABASE, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Errore connessione DB: " + e.getMessage());
            return null;
        }
    }

    public static void creaDatabase() {
        try (Connection conn = DriverManager.getConnection(URL_SERVER, USER, PASSWORD);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE);
            System.out.println("Database '" + DATABASE + "' pronto.");
        } catch (Exception e) {
            System.out.println("Errore creazione database: " + e.getMessage());
        }
    }

    public static void creaTabellaRistoranti() {
        String sql = "CREATE TABLE IF NOT EXISTS ristoranti (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL UNIQUE)";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabella 'ristoranti' pronta.");
        } catch (Exception e) {
            System.out.println("Errore creazione tabella ristoranti: " + e.getMessage());
        }
    }
}