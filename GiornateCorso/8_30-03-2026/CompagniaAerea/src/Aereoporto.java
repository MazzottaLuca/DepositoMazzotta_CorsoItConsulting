import java.sql.*;

public class Aereoporto {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/databasecompagniaaerea";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            // Creazione compagnia
            CompagniaAerea compagnia = new CompagniaAerea("Ryanair");
            String sqlComp = "INSERT INTO compagnia_aerea(nome) VALUES (?)";
            PreparedStatement psComp = conn.prepareStatement(sqlComp, Statement.RETURN_GENERATED_KEYS);
            psComp.setString(1, compagnia.getNome());
            psComp.executeUpdate();

            ResultSet rsComp = psComp.getGeneratedKeys();
            int compagniaId = 0;
            if (rsComp.next()) {
                compagniaId = rsComp.getInt(1);
            }

            // Creazione aerei

            Aereo a1 = new Aereo("Boeing 737", 180, "A001");
            Aereo a2 = new Aereo("Airbus A320", 160, "A002");

            String sqlAereo = "INSERT INTO aereo(modello, numero_posti, codice, compagnia_id) VALUES (?, ?, ?, ?)";
            PreparedStatement psAereo = conn.prepareStatement(sqlAereo);

            psAereo.setString(1, a1.getModello());
            psAereo.setInt(2, a1.getNumeroPosti());
            psAereo.setString(3, a1.getCodice());
            psAereo.setInt(4, compagniaId);
            psAereo.executeUpdate();

            psAereo.setString(1, a2.getModello());
            psAereo.setInt(2, a2.getNumeroPosti());
            psAereo.setString(3, a2.getCodice());
            psAereo.setInt(4, compagniaId);
            psAereo.executeUpdate();

            // Creazione piloti

            Pilota p1 = new Pilota("Mario Rossi", "BR123", 1500);
            Pilota p2 = new Pilota("Luca Bianchi", "BR456", 2000);

            String sqlPilota = "INSERT INTO pilota(nome, numero_brevetto, ore_volo, compagnia_id) VALUES (?, ?, ?, ?)";
            PreparedStatement psPilota = conn.prepareStatement(sqlPilota);

            psPilota.setString(1, p1.getNome());
            psPilota.setString(2, p1.getNumeroBrevetto());
            psPilota.setInt(3, p1.getOreVolo());
            psPilota.setInt(4, compagniaId);
            psPilota.executeUpdate();

            psPilota.setString(1, p2.getNome());
            psPilota.setString(2, p2.getNumeroBrevetto());
            psPilota.setInt(3, p2.getOreVolo());
            psPilota.setInt(4, compagniaId);
            psPilota.executeUpdate();

            conn.commit();
            System.out.println("Dati salvati nel database!\n");

            // Stampa compagnia
            String queryComp = "SELECT * FROM compagnia_aerea WHERE id = ?";
            PreparedStatement psReadComp = conn.prepareStatement(queryComp);
            psReadComp.setInt(1, compagniaId);
            ResultSet rsReadComp = psReadComp.executeQuery();
            if (rsReadComp.next()) {
                System.out.println("Compagnia: " + rsReadComp.getString("nome"));
            }

            // Stampa aerei
            String queryAerei = "SELECT * FROM aereo WHERE compagnia_id = ?";
            PreparedStatement psReadAerei = conn.prepareStatement(queryAerei);
            psReadAerei.setInt(1, compagniaId);
            ResultSet rsAerei = psReadAerei.executeQuery();
            System.out.println("\nAerei:");
            while (rsAerei.next()) {
                System.out.println("- " + rsAerei.getString("modello") +
                        " (" + rsAerei.getInt("numero_posti") + " posti) - Codice: " + rsAerei.getString("codice"));
            }

            // Stampa piloti
            String queryPiloti = "SELECT * FROM pilota WHERE compagnia_id = ?";
            PreparedStatement psReadPiloti = conn.prepareStatement(queryPiloti);
            psReadPiloti.setInt(1, compagniaId);
            ResultSet rsPiloti = psReadPiloti.executeQuery();
            System.out.println("\nPiloti:");
            while (rsPiloti.next()) {
                System.out.println("- " + rsPiloti.getString("nome") +
                        " | Brevetto: " + rsPiloti.getString("numero_brevetto") +
                        " | Ore di volo: " + rsPiloti.getInt("ore_volo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}