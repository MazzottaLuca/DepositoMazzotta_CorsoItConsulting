import java.sql.*;

public class DBSetup {

    public static void stampaTuttiDalDB() {
        String sql = "SELECT tipo, targa, velocita, numero_assi, attributo_extra, pedaggio FROM veicoli";
        System.out.println("\n=== VEICOLI NEL DATABASE ===");
        try (Connection conn = DBConn.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            boolean trovati = false;
            while (rs.next()) {
                trovati = true;
                System.out.printf("[%s] Targa: %s | Vel: %.1f | Assi: %d | %s | Pedaggio: €%.2f%n",
                        rs.getString("tipo"),
                        rs.getString("targa"),
                        rs.getDouble("velocita"),
                        rs.getInt("numero_assi"),
                        rs.getString("attributo_extra"),
                        rs.getDouble("pedaggio"));
            }
            if (!trovati)
                System.out.println("Nessun veicolo nel database.");
        } catch (SQLException e) {
            System.err.println("✘ Errore lettura: " + e.getMessage());
        }
    }
}