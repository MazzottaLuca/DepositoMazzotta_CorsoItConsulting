import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        GestoreOrdini gestore = new GestoreOrdini();

        // aggiungi observer
        gestore.addObserver(new Magazzino());
        gestore.addObserver(new Spedizioni());
        gestore.addObserver(new SistemaNotifiche());

        OrdineDAO dao = new OrdineDAO();

        int scelta;
        do {
            System.out.println("1 - Inserisci nuovo ordine");
            System.out.println("2 - Visualizza ordine per ID");
            System.out.println("3 - Aggiorna stato ordine");
            System.out.println("4 - Esci");
            scelta = sc.nextInt();
            sc.nextLine(); // pulizia buffer

            switch (scelta) {
                case 1:
                    System.out.print("Cliente: ");
                    String cliente = sc.nextLine();
                    System.out.print("Prodotto: ");
                    String prodotto = sc.nextLine();
                    System.out.print("Quantità: ");
                    int quantita = sc.nextInt();
                    sc.nextLine();
                    Ordine ordine = new Ordine(cliente, prodotto, quantita, "CREATO");
                    dao.inserisciOrdine(ordine);
                    gestore.notifyObservers(ordine);
                    break;
                case 2:
                    System.out.print("ID ordine: ");
                    int id = sc.nextInt();
                    Ordine o = dao.getOrdine(id);
                    if (o != null)
                        System.out.println("Ordine trovato: " + o.getCliente() + ", " + o.getStato());
                    else
                        System.out.println("Ordine non trovato");
                    break;
                case 3:
                    System.out.print("ID ordine: ");
                    int idUpd = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuovo stato: ");
                    String stato = sc.nextLine();
                    dao.aggiornaStato(idUpd, stato);
                    Ordine o2 = dao.getOrdine(idUpd);
                    gestore.notifyObservers(o2);
                    break;
                case 4:
                    System.out.println("uscita dal programma");
                    break;
                default: 
                System.out.println("Scelta non valida");
                break;
            }
        } while (scelta != 4);

        sc.close();
    }
}