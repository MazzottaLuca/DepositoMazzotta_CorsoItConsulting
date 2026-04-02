import java.sql.*;

public class DBConn {

    private static final String URL_NO_DB =
            "jdbc:mysql://localhost:3306";

    private static final String URL =
            "jdbc:mysql://localhost:3306/sartoria";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void setup() {
        creaDatabase();
        creaTabelle();
    }

    private static void creaDatabase() {
        String sql = "CREATE DATABASE IF NOT EXISTS sartoria";

        try (Connection conn = DriverManager.getConnection(URL_NO_DB, USER, PASSWORD);
             Statement st = conn.createStatement()) {

            st.executeUpdate(sql);
            System.out.println("✔ Database creato");

        } catch (SQLException e) {
            System.out.println("Errore DB: " + e.getMessage());
        }
    }

    private static void creaTabelle() {

        String capi = "CREATE TABLE IF NOT EXISTS capi_principali (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(100)," +
                "prezzo DOUBLE)";

        String componenti = "CREATE TABLE IF NOT EXISTS componenti (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(100)," +
                "prezzo DOUBLE)";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate(capi);
            st.executeUpdate(componenti);

            System.out.println("✔ Tabelle create");

        } catch (SQLException e) {
            System.out.println("Errore tabelle: " + e.getMessage());
        }
    }
}