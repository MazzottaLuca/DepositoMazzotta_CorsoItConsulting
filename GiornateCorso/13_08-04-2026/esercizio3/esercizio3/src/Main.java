import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        
        DatabaseSetup.inizializzaDatabase();

        GestoreOrdini gestore = GestoreOrdini.getInstance();

        // Observer
        gestore.addObserver(new ClienteObserver("Barista"));

        Bevanda bevanda = null;

        while (true) {
            System.out.println("\n1. Nuova bevanda");
            System.out.println("2. Aggiungi extra");
            System.out.println("3. Visualizza");
            System.out.println("4. Conferma ordine");
            System.out.println("5. Storico ordini");
            System.out.println("6. Esci");

            int scelta = scanner.nextInt();

            switch (scelta) {

                case 1:
                    System.out.println("1. Caffè  2. Tè  3. Cioccolata");
                    int tipo = scanner.nextInt();

                    if (tipo == 1) bevanda = new Caffe();
                    else if (tipo == 2) bevanda = new Te();
                    else if (tipo == 3) bevanda = new Cioccolata();
                    else System.out.println("Scelta non valida");

                    break;

                case 2:
                    if (bevanda == null) {
                        System.out.println("Prima crea una bevanda!");
                        break;
                    }

                    System.out.println("1. Latte  2. Zucchero  3. Panna  4. Cannella");
                    int extra = scanner.nextInt();

                    if (extra == 1) bevanda = new Latte(bevanda);
                    else if (extra == 2) bevanda = new Zucchero(bevanda);
                    else if (extra == 3) bevanda = new Panna(bevanda);
                    else if (extra == 4) bevanda = new Cannella(bevanda);
                    else System.out.println("Extra non valido");

                    break;

                case 3:
                    if (bevanda != null) {
                        System.out.println("Bevanda attuale:");
                        System.out.println(bevanda.getDescrizione() + " - €" + bevanda.getCosto());
                    } else {
                        System.out.println("Nessuna bevanda creata");
                    }
                    break;

                case 4:
                    if (bevanda != null) {
                        gestore.aggiungiOrdine(bevanda);
                        System.out.println("Ordine confermato!");
                        bevanda = null;
                    } else {
                        System.out.println("Nessuna bevanda da confermare");
                    }
                    break;

                case 5:
                    System.out.println("Storico ordini (dal database):");
                    OrdineDAO.stampaOrdini(); 
                    break;

                case 6:
                    System.out.println("Uscita...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Scelta non valida");
            }
        }
    }
}