import java.sql.*;

public class Astronauta {
    protected int id;
    protected String nome;
    protected String email;
    protected float creditoOssigeno;
    protected int azioni;

    public Astronauta(String nome, String email) {
        this.nome = nome;
        this.email = email;
        generaOssigeno();
        this.azioni = 0;
    }

    // Genera ossigeno casuale (tra 50 e 100)
    public void generaOssigeno() {
        creditoOssigeno = 50 + (float) (Math.random() * 50); //valori tra 0 e 1
    }

    // Salvataggio su DB
    public void salvaSuDB(String ruolo) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO astronauti(nome, email, ruolo, ossigeno, azioni) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, ruolo);
            ps.setFloat(4, creditoOssigeno);
            ps.setInt(5, azioni);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Login
    public static Astronauta login(String email) {
        try (Connection conn = DBConnection.getConnection()) {

            String query = "SELECT * FROM astronauti WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String ruolo = rs.getString("ruolo");

                Astronauta a;

                //  Gestione ruoli (anche evoluti)
                if (ruolo.equalsIgnoreCase("Scienziato") || ruolo.equalsIgnoreCase("ScienziatoCapo")) {
                    a = new Scienziato(nome, email);
                } else {
                    a = new Ispettore(nome, email);
                }

                a.id = rs.getInt("id");
                a.azioni = rs.getInt("azioni");

                //  Rigenera ossigeno ad ogni login
                a.generaOssigeno();
                a.aggiornaOssigenoDB();

                return a;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //  Aggiorna ossigeno nel DB
    public void aggiornaOssigenoDB() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE astronauti SET ossigeno = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setFloat(1, creditoOssigeno);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Incrementa azioni
    public void incrementaAzioni() {
        azioni++;

        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE astronauti SET azioni = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, azioni);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Controlla evoluzione
    public void controllaEvoluzione() {
        if (azioni >= 3) {
            try (Connection conn = DBConnection.getConnection()) {

                String nuovoRuolo = "";

                if (this instanceof Scienziato) {
                    nuovoRuolo = "ScienziatoCapo";
                } else if (this instanceof Ispettore) { //this.instanceof controlla se l'oggetto è un'istanza della classe scienziato o della sottoclasse
                    nuovoRuolo = "IspettoreEsperto";
                }

                String query = "UPDATE astronauti SET ruolo = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, nuovoRuolo);
                ps.setInt(2, id);
                ps.executeUpdate();

                System.out.println("Evoluzione avvenuta! Nuovo ruolo: " + nuovoRuolo);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //  Stampa dati
    public void stampaDati() {
        try (Connection conn = DBConnection.getConnection()) {

            String query = "SELECT nome, email, ruolo, ossigeno, azioni FROM astronauti WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- DATI ASTRONAUTA ---");
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Ruolo: " + rs.getString("ruolo"));
                System.out.println("Ossigeno: " + rs.getFloat("ossigeno"));
                System.out.println("Azioni: " + rs.getInt("azioni"));
            }

            //  Esperimenti
            if (this instanceof Scienziato) {
                System.out.println("\nEsperimenti:");
                query = "SELECT nome FROM esperimenti WHERE astronauta_id = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(" - " + rs.getString("nome"));
                }
            }

            //  Valutazioni
            if (this instanceof Ispettore) {
                System.out.println("\nValutazioni:");
                query = "SELECT voto FROM valutazioni WHERE astronauta_id = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(" - " + rs.getInt("voto"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}