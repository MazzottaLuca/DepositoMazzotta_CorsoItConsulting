package scuola;
import java.sql.*;

public class VotoDAO {

    public void inserisciVoto(String materia, String data, float voto, int idStudente) {
        String query = "INSERT INTO Voto (materia, data, valore, id_studente) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, materia);
            ps.setDate(2, Date.valueOf(data));
            ps.setFloat(3, voto);
            ps.setInt(4, idStudente);

            ps.executeUpdate();
            System.out.println("Voto inserito!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificaVoto(int id, float nuovo) {
        String query = "UPDATE Voto SET valore = ? WHERE id_voto = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setFloat(1, nuovo);
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Voto aggiornato!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}