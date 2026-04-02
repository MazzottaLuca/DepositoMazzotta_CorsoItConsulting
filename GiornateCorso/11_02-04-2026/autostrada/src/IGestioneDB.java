import java.sql.*;

public interface IGestioneDB {
    default void salvaDB() {
        String sql = """
                INSERT INTO veicoli (tipo, targa, velocita, numero_assi, attributo_extra, pedaggio)
                VALUES (?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    velocita        = VALUES(velocita),
                    attributo_extra = VALUES(attributo_extra),
                    pedaggio        = VALUES(pedaggio)
                """;
        try (Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, getTipo());
            ps.setString(2, getTarga());
            ps.setDouble(3, getVelocita());
            ps.setInt(4, getNumeroAssi());
            ps.setString(5, getAttributoExtra());
            ps.setDouble(6, calcolaPedaggio());
            ps.executeUpdate();
            System.out.println("Salvato: " + getTarga());
        } catch (SQLException e) {
            System.err.println("Errore salvataggio: " + e.getMessage());
        }
    }

    default void aggiornaDB() {
        String sql = "UPDATE veicoli SET velocita=?, attributo_extra=?, pedaggio=? WHERE targa=?";
        try (Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, getVelocita());
            ps.setString(2, getAttributoExtra());
            ps.setDouble(3, calcolaPedaggio());
            ps.setString(4, getTarga());
            ps.executeUpdate();
            System.out.println("Aggiornato: " + getTarga());
        } catch (SQLException e) {
            System.err.println("Errore aggiornamento: " + e.getMessage());
        }
    }

    default void eliminaDB() {
        String sql = "DELETE FROM veicoli WHERE targa=?";
        try (Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, getTarga());
            ps.executeUpdate();
            System.out.println("Eliminato: " + getTarga());
        } catch (SQLException e) {
            System.err.println("Errore eliminazione: " + e.getMessage());
        }
    }

    String getTipo();

    String getTarga();

    double getVelocita();

    int getNumeroAssi();

    String getAttributoExtra();

    double calcolaPedaggio();
}