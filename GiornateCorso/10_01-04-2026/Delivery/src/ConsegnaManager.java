import java.util.ArrayList;
import java.util.Scanner;

// Classe principale che gestisce il programma
public class ConsegnaManager {
    public static void main(String[] args) {

        // Inizializza il database
        DBSetup.setupDatabase();

        ArrayList<VeicoloConsegna> veicoli = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        boolean esci = false;

        while (!esci) {
            System.out.println("==== MENU CONSEGNE ====");
            System.out.println("1. Aggiungi veicolo");
            System.out.println("2. Effettua consegna");
            System.out.println("3. Mostra veicoli");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            int scelta = sc.nextInt();
            sc.nextLine();

            switch (scelta) {

                // === AGGIUNTA VEICOLO ===
                case 1 -> {
                    System.out.println("Scegli veicolo (1: Furgone, 2: Drone): ");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    // Inserimento dati del veicolo
                    System.out.print("Inserisci targa: ");
                    String targa = sc.nextLine();

                    System.out.print("Inserisci carico massimo (kg): ");
                    float carico = sc.nextFloat();
                    sc.nextLine();

                    // Creazione oggetto in base al tipo scelto
                    VeicoloConsegna v = (tipo == 1)
                            ? new Furgone(targa, carico)
                            : new Drone(targa, carico);

                    veicoli.add(v);

                    System.out.println("Veicolo aggiunto!\n");
                }

                // === EFFETTUA CONSEGNA ===
                case 2 -> {
                    // Controlla se ci sono veicoli disponibili
                    if (veicoli.isEmpty()) {
                        System.out.println("Nessun veicolo disponibile.\n");
                        break;
                    }

                    // Mostra lista veicoli
                    System.out.println("Seleziona veicolo:");
                    for (int i = 0; i < veicoli.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        veicoli.get(i).stampaInfo();
                    }

                    // Scelta del veicolo
                    int index = sc.nextInt() - 1;
                    sc.nextLine();

                    // Controllo validità indice
                    if (index < 0 || index >= veicoli.size()) {
                        System.out.println("Veicolo non valido!");
                        break;
                    }

                    // Recupero veicolo selezionato
                    VeicoloConsegna veicolo = veicoli.get(index);

                    // Inserimento dati consegna
                    System.out.print("Destinazione: ");
                    String dest = sc.nextLine();

                    System.out.print("Peso pacco: ");
                    float peso = sc.nextFloat();
                    sc.nextLine();

                    System.out.print("Codice tracking: ");
                    String track = sc.nextLine();

                    // Esegue la consegna
                    boolean successo = veicolo.consegnaPacco(dest, peso, track);

                    // Se il veicolo supporta il tracking, traccia la consegna
                    if (successo && veicolo instanceof Tracciabile) {
                        ((Tracciabile) veicolo).tracciaConsegna(track);
                    }
                }

                // === MOSTRA VEICOLI ===
                case 3 -> {
                    if (veicoli.isEmpty())
                        System.out.println("Nessun veicolo registrato.\n");
                    else {
                        System.out.println("Veicoli registrati:");
                        for (VeicoloConsegna v : veicoli)
                            v.stampaInfo();
                        System.out.println();
                    }
                }

                case 0 -> esci = true;

                default -> System.out.println("Scelta non valida!\n");
            }
        }

        System.out.println("Programma terminato.");
        sc.close();
    }
}