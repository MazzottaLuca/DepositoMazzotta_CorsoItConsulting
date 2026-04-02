package scuola;
import java.sql.*;

public class StudenteDAO {

    public void inserisciStudente(String nome, String cognome, String data, int idClasse, String cf) {
        String query = "INSERT INTO Studente (nome, cognome, data_di_nascita, id_classe, codice_fiscale) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setDate(3, Date.valueOf(data));
            ps.setInt(4, idClasse);
            ps.setString(5, cf);

            ps.executeUpdate();
            System.out.println("Studente inserito!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}