import java.sql.*;
public class Scienziato extends Astronauta {

    public Scienziato(String nome, String email) {
        super(nome, email);
    }

    // Aggiunge esperimento
    public void aggiungiEsperimento(String exp) {
        try (Connection conn = DBConnection.getConnection()) {

            String query = "INSERT INTO esperimenti(astronauta_id, nome) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, exp);
            ps.executeUpdate();

            System.out.println("Esperimento aggiunto!");

            // aggiorna azioni + controlla evoluzione
            incrementaAzioni();
            controllaEvoluzione();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Funzione avanzata (solo ScienziatoCapo)
    public void stampaTuttiEsperimenti() {
        try (Connection conn = DBConnection.getConnection()) {

            String queryRuolo = "SELECT ruolo FROM astronauti WHERE id = ?";
            PreparedStatement psRuolo = conn.prepareStatement(queryRuolo);
            psRuolo.setInt(1, id);
            ResultSet rsRuolo = psRuolo.executeQuery();

            if (rsRuolo.next()) {
                String ruolo = rsRuolo.getString("ruolo");

                if (ruolo.equalsIgnoreCase("ScienziatoCapo")) {

                    System.out.println("\n--- TUTTI GLI ESPERIMENTI ---");

                    String query = "SELECT nome FROM esperimenti";
                    PreparedStatement ps = conn.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        System.out.println(" - " + rs.getString("nome"));
                    }

                } else {
                    System.out.println("Non sei ancora ScienziatoCapo");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}