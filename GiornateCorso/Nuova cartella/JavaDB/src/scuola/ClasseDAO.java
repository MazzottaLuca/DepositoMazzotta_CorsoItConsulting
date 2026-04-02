package scuola;
import java.sql.*;

public class ClasseDAO {

    public void inserisciClasse(String nome, String anno, String sezione, int capienza) {
        String query = "INSERT INTO Classe (nome, anno_scolastico, sezione, capienza) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nome);
            ps.setString(2, anno);
            ps.setString(3, sezione);
            ps.setInt(4, capienza);

            ps.executeUpdate();
            System.out.println("Classe inserita!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}