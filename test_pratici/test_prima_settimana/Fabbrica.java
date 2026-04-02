import java.util.ArrayList;
import java.util.Scanner;

public class Fabbrica{
    static Scanner scanner = new Scanner(System.in); //lo dichiaro static perchè i metodi in cui lo uso sono static
    public static void main(String[] args) {
        
        ArrayList<String> prodotti = new ArrayList<>();
        ArrayList<Integer> quantita = new ArrayList<>();
        int scelta = 0;
        while (scelta != 7) { // uso il ciclo while per mantenere il programma attivo
            System.out.println("1- aggiungi prodotto");
            System.out.println("2- visualizza produzione");
            System.out.println("3- cerca prodotto");
            System.out.println("4- modifica quantità");
            System.out.println("5- rimuovi prodotto");
            System.out.println("6- calcolo totale pezzi prodotto");
            System.out.println("7- esci");
            scelta = scanner.nextInt();
            scanner.nextLine();
            switch (scelta) { // switch per scegliere le opzioni del programma
                case 1:
                    aggiungiProdotto(prodotti, quantita); // richiamo il metodo aggiungiprodotto
                    break;
                case 2:
                    mostraProdotti(prodotti, quantita); // richiamo il metodo mostraProdotti
                    break;
                case 3:
                    cercaProdotto(prodotti, quantita); // richiamo il metodo cercaProdotto
                    break;
                case 4:
                    modificaQuantita(prodotti, quantita); // richiamo il metodo modificaProdotti
                    break;
                case 5:
                    rimuoviProdotto(prodotti, quantita); // richiamo il metodo rimuoviProdotto
                    break;
                case 6:
                    totalePezzi(quantita); // richiamo il metodo totalePezzi
                    break;
                case 7:
                    System.out.println("Sei uscito");
                    break;
                default:
                    System.out.println("comando non valido");

            }
        }
    }

    public static void totalePezzi(ArrayList<Integer> quantita) {
        // TODO Auto-generated method stub
        int totale = 0;
        for (int i = 0; i < quantita.size(); i++) {
            totale += quantita.get(i);
        }
        System.out.println("Il numero totale dei pezzi è:" + totale);
    }

    public static void rimuoviProdotto(ArrayList<String> prodotti, ArrayList<Integer> quantita) {
        // TODO Auto-generated method stub
        System.out.println("Scrivi il nome del prodotto che vuoi rimuovere");
        String nome = scanner.nextLine();
        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).equalsIgnoreCase(nome)) {
                prodotti.remove(i);
                quantita.remove(i);
                System.out.println("prodotto " + nome + "rimosso");
                return;
            }
        }
        System.out.println("prodotto non trovato");
    }

    public static void modificaQuantita(ArrayList<String> prodotti, ArrayList<Integer> quantita) {
        // TODO Auto-generated method stub
        System.out.print("Nome prodotto da modificare: ");
        String nome = scanner.nextLine();

        for (int i = 0; i < prodotti.size(); i++) {

            if (prodotti.get(i).equalsIgnoreCase(nome)) {

                System.out.print("Nuova quantità: ");
                int nuovaQ = scanner.nextInt();
                scanner.nextLine();

                if (nuovaQ < 0) {
                    System.out.println("Errore: quantità negativa!");
                    return;
                }

                quantita.set(i, nuovaQ);
                System.out.println("Quantità aggiornata!");
                return;
            }
        }

        System.out.println("Prodotto non trovato");

    }

    public static void cercaProdotto(ArrayList<String> prodotti, ArrayList<Integer> quantita) {
        // TODO Auto-generated method stub
        System.out.print("Inserisci nome prodotto: ");
        String nome = scanner.nextLine();

        boolean trovato = false;

        for (int i = 0; i < prodotti.size(); i++) {
            if (prodotti.get(i).equalsIgnoreCase(nome)) {
                System.out.println("Prodotto trovato: " + prodotti.get(i) + " - Quantità: " + quantita.get(i));
                trovato = true;
            }
        }

        if (!trovato) {
            System.out.println("Prodotto non trovato");
        }
    }

    public static void mostraProdotti(ArrayList<String> prodotti, ArrayList<Integer> quantita) {
        // TODO Auto-generated method stub
        if (prodotti.size() == 0) {
            System.out.println("Nessun prodotto presente.");
            return;
        }

        for (int i = 0; i < prodotti.size(); i++) {
            System.out.println(prodotti.get(i) + " - Quantità: " + quantita.get(i));
        }
    }

    public static void aggiungiProdotto(ArrayList<String> prodotti, ArrayList<Integer> quantita) {
        // TODO Auto-generated method stub
        System.out.print("Nome prodotto: ");
        String nome = scanner.nextLine();

        if (nome.isEmpty()) {
            System.out.println("Errore: nome vuoto!");
            return;
        }

        System.out.print("Quantità: ");
        int q = scanner.nextInt();
        scanner.nextLine();

        if (q < 0) {
            System.out.println("Errore: quantità negativa!");
            return;
        }

        prodotti.add(nome);
        quantita.add(q);

        System.out.println("Prodotto aggiunto!");
    }

}
