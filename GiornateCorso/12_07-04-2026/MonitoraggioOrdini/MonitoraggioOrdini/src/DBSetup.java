import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSetup {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = ""; 

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement()) {

            // Creazione database
            String sqlDB = "CREATE DATABASE IF NOT EXISTS magazzino";
            stmt.executeUpdate(sqlDB);

            // Seleziona il database
            stmt.execute("USE magazzino");

            // Creazione tabella ordini
            String sqlTable = "CREATE TABLE IF NOT EXISTS ordini (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "cliente VARCHAR(100)," +
                    "prodotto VARCHAR(100)," +
                    "quantita INT," +
                    "stato VARCHAR(50)" +
                    ")";
            stmt.executeUpdate(sqlTable);

            System.out.println("Database e tabella creati correttamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}