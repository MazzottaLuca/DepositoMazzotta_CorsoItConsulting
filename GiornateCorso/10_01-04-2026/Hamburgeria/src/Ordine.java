import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Ordine {

    public static void creaTabella() {
        String sql = "CREATE TABLE IF NOT EXISTS ordini (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "nome VARCHAR(255) NOT NULL, " +
                     "ristorante_id INT NOT NULL, " +
                     "quantita INT NOT NULL, " +
                     "FOREIGN KEY (ristorante_id) REFERENCES ristoranti(id))";
        try (Connection conn = DB.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabella 'ordini' pronta.");
        } catch (Exception e) {
            System.out.println("Errore creazione tabella ordini: " + e.getMessage());
        }
    }

    public static void salva(String nome, int ristoranteId, int quantita) {
        String sql = "INSERT INTO ordini(nome, ristorante_id, quantita) VALUES(?, ?, ?)";
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, ristoranteId);
            stmt.setInt(3, quantita);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Errore salvataggio ordine: " + e.getMessage());
        }
    }
}