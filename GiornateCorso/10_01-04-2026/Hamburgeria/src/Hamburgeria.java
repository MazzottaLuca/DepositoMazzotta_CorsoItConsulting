import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Hamburgeria {

    public static void main(String[] args) {

        DB.creaDatabase();
        DB.creaTabellaRistoranti();
        Ordine.creaTabella();

        Scanner scanner = new Scanner(System.in);
        ArrayList<OrdineProdotto> ordini = new ArrayList<>();

        // --- Inserimento ristoranti di esempio se tabella vuota ---
        try (Connection conn = DB.connect();
                Statement stmt = conn.createStatement()) {

            ResultSet rsCount = stmt.executeQuery("SELECT COUNT(*) AS totale FROM ristoranti");
            rsCount.next();
            int totale = rsCount.getInt("totale");

            if (totale == 0) {
                String[] nomiRistoranti = { "Burger King", "Happy Meals", "Grill House" };
                String sqlInsert = "INSERT INTO ristoranti(nome) VALUES(?)";
                try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
                    for (String nome : nomiRistoranti) {
                        ps.setString(1, nome);
                        ps.executeUpdate();
                    }
                }
                System.out.println("Ristoranti di esempio inseriti nel database.");
            }

        } catch (Exception e) {
            System.out.println("Errore inserimento ristoranti: " + e.getMessage());
        }

        // --- Selezione ristorante con validazione ---
        int ristoranteId = 0;
        ArrayList<Integer> idRistoranti = new ArrayList<>();
        try (Connection conn = DB.connect();
                Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM ristoranti");
            System.out.println("\n--- RISTORANTI DISPONIBILI ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                idRistoranti.add(id);
                System.out.println(id + " - " + nome);
            }

            do {
                System.out.print("Inserisci l'id del ristorante da cui vuoi ordinare: ");
                ristoranteId = scanner.nextInt();
                if (!idRistoranti.contains(ristoranteId)) {
                    System.out.println("ID non valido. Riprova.");
                }
            } while (!idRistoranti.contains(ristoranteId));

        } catch (Exception e) {
            System.out.println("Errore lettura ristoranti: " + e.getMessage());
            return;
        }

        // --- Menu ordini ---
        int scelta;
        do {
            System.out.println("\n--- MENU HAMBURGER ---");
            System.out.println("1. Cheeseburger (disponibili 10)");
            System.out.println("2. VegBurger (disponibili 8)");
            System.out.println("3. DoubleBacon (disponibili 5)");
            System.out.println("\n--- MENU DESSERT ---");
            System.out.println("4. Gelato (disponibili 20)");
            System.out.println("5. Torta (disponibili 7)");
            System.out.println("0. Fine ordine");
            System.out.print("Scelta: ");
            scelta = scanner.nextInt();

            Object prodotto = null;

            switch (scelta) {
                case 1:
                    prodotto = new Cheeseburger(10);
                    break;
                case 2:
                    prodotto = new VegBurger(8);
                    break;
                case 3:
                    prodotto = new DoubleBacon(5);
                    break;
                case 4:
                    prodotto = new Gelato(20);
                    break;
                case 5:
                    prodotto = new Torta(7);
                    break;
                case 0:
                    System.out.println("Chiusura ordine...");
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }

            if (prodotto != null) {
                System.out.print("Quante unità vuoi ordinare? ");
                int quantita = scanner.nextInt();

                int disp = prodotto instanceof Hamburger ? ((Hamburger) prodotto).getDisponibilita()
                        : ((Dessert) prodotto).getDisponibilita();

                if (quantita <= disp) {
                    OrdineProdotto op = new OrdineProdotto(prodotto, quantita);
                    ordini.add(op);
                    Ordine.salva(op.getNome(), ristoranteId, quantita);

                    if (prodotto instanceof Hamburger)
                        ((Hamburger) prodotto).setDisponibilita(disp - quantita);
                    else
                        ((Dessert) prodotto).setDisponibilita(disp - quantita);
                } else {
                    System.out.println("Quantità non disponibile. Disponibili: " + disp);
                }
            }

        } while (scelta != 0);

        // --- Preparazione ordini ---
        System.out.println("\n--- PREPARAZIONE ORDINI ---");
        for (OrdineProdotto op : ordini) {
            op.prepara();
        }

        scanner.close();
    }
}