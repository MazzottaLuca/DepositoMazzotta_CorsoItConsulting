import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    
    public static void inizializzaDatabase() {
        creaDatabase();
        creaTabelle();
    }

    
    private static void creaDatabase() {

        String sql = "CREATE DATABASE IF NOT EXISTS bar_db";

        try (Connection conn = DatabaseManager.connectServer();
                Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Database pronto");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private static void creaTabelle() {

        // Tabella ORDINI
        String ordini = """
                    CREATE TABLE IF NOT EXISTS ordini (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        descrizione VARCHAR(255),
                        costo DOUBLE,
                        data TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )
                """;

        // Tabella INGREDIENTI 
        String ingredienti = """
                    CREATE TABLE IF NOT EXISTS ingredienti (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(100),
                        prezzo DOUBLE,
                        ordine_id INT,
                        FOREIGN KEY (ordine_id) REFERENCES ordini(id)
                        ON DELETE CASCADE
                    )
                """;

        try (Connection conn = DatabaseManager.connectDB();
                Statement stmt = conn.createStatement()) {

            
            stmt.executeUpdate(ordini);
            stmt.executeUpdate(ingredienti);

            System.out.println("Tabelle pronte");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}