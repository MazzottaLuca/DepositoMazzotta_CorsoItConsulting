import java.sql.*;

public class Ispettore extends Astronauta {

    public Ispettore(String nome, String email) {
        super(nome, email);
    }

    // Aggiunge valutazione (1-5)
    public void aggiungiValutazione(int voto) {
        try (Connection conn = DBConnection.getConnection()) {

            if (voto < 1 || voto > 5) {
                System.out.println("Il voto deve essere tra 1 e 5");
                return;
            }

            String query = "INSERT INTO valutazioni(astronauta_id, voto) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, voto);
            ps.executeUpdate();

            System.out.println("Valutazione inserita!");

            // aggiorna azioni + controlla evoluzione
            incrementaAzioni();
            controllaEvoluzione();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Funzione avanzata (solo IspettoreEsperto)
    public void stampaTutteValutazioni() {
        try (Connection conn = DBConnection.getConnection()) {

            String queryRuolo = "SELECT ruolo FROM astronauti WHERE id = ?";
            PreparedStatement psRuolo = conn.prepareStatement(queryRuolo);
            psRuolo.setInt(1, id);
            ResultSet rsRuolo = psRuolo.executeQuery();

            if (rsRuolo.next()) {
                String ruolo = rsRuolo.getString("ruolo");

                if (ruolo.equalsIgnoreCase("IspettoreEsperto")) {

                    System.out.println("\n--- TUTTE LE VALUTAZIONI ---");

                    String query = "SELECT voto FROM valutazioni";
                    PreparedStatement ps = conn.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        System.out.println(" - " + rs.getInt("voto"));
                    }

                } else {
                    System.out.println("Non sei ancora IspettoreEsperto");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}