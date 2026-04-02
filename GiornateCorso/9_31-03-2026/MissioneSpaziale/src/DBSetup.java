import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSetup {

    public static void setupDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
            Statement stmt = conn.createStatement();

            // 1. Creazione database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS missione_spaziale");
            stmt.executeUpdate("USE missione_spaziale");

            // 2. Tabella astronauti 
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS astronauti(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(50),
                    email VARCHAR(50) UNIQUE,
                    ruolo VARCHAR(20),
                    ossigeno FLOAT,
                    azioni INT DEFAULT 0
                )
            """);

            // 3. Tabella esperimenti 
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS esperimenti(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    astronauta_id INT,
                    nome VARCHAR(100),
                    FOREIGN KEY (astronauta_id) 
                    REFERENCES astronauti(id) 
                    ON DELETE CASCADE
                )
            """);

            // 4. Tabella valutazioni 
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS valutazioni(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    astronauta_id INT,
                    voto INT CHECK (voto >= 1 AND voto <= 5),
                    FOREIGN KEY (astronauta_id) 
                    REFERENCES astronauti(id) 
                    ON DELETE CASCADE
                )
            """);

            System.out.println("Database e tabelle creati con successo!");
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}