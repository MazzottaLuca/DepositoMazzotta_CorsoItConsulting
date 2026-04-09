import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrdineDAO {

    
    public static void salvaOrdine(Bevanda bevanda) {

        String sql = "INSERT INTO ordini (descrizione, costo) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.connectDB();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bevanda.getDescrizione());
            stmt.setDouble(2, bevanda.getCosto());

            stmt.executeUpdate();

            System.out.println("Ordine salvato nel database");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void stampaOrdini() {

        String sql = "SELECT * FROM ordini";

        try (Connection conn = DatabaseManager.connectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("descrizione") + " | €" +
                                rs.getDouble("costo") + " | " +
                                rs.getTimestamp("data"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}