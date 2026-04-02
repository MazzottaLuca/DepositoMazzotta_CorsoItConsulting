import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //  Setup DB
        DBSetup.setupDatabase();//permette l'esecuzione delle query senza dover eseguire dbsetup

        Scanner sc = new Scanner(System.in);
        Astronauta utente = null;

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Login / Registrazione");
            System.out.println("2. Visualizza dati astronauta");
            System.out.println("3. Rigenera ossigeno");
            System.out.println("4. Interagisci");
            System.out.println("5. Esci");
            System.out.print("Scegli un'opzione: ");

            int scelta = sc.nextInt();
            sc.nextLine(); // pulisce il buffer

            switch (scelta) {

                case 1: // LOGIN / REGISTRAZIONE
                    System.out.print("Inserisci la tua email: ");
                    String email = sc.nextLine();
                    utente = Astronauta.login(email);

                    if (utente != null) {
                        System.out.println("Login effettuato con successo!");
                        System.out.println("Ossigeno attuale: " + utente.creditoOssigeno);
                    } else {
                        System.out.print("Non trovato! Inserisci il tuo nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Sei Scienziato o Ispettore? ");
                        String ruolo = sc.nextLine();

                        if (ruolo.equalsIgnoreCase("Scienziato")) {
                            utente = new Scienziato(nome, email);
                            utente.salvaSuDB("Scienziato");
                        } else {
                            utente = new Ispettore(nome, email);
                            utente.salvaSuDB("Ispettore");
                        }
                        System.out.println("Registrazione completata!");
                    }
                    break;

                case 2: // VISUALIZZA DATI
                    if (utente != null) {
                        utente.stampaDati();
                    } else {
                        System.out.println("Devi prima fare login!");
                    }
                    break;

                case 3: // RIGENERA OSSIGENO
                    if (utente != null) {
                        utente.generaOssigeno();
                        utente.aggiornaOssigenoDB();
                        System.out.println("Ossigeno rigenerato: " + utente.creditoOssigeno);
                    } else {
                        System.out.println("Devi prima fare login!");
                    }
                    break;

                case 4: // INTERAZIONE
                    if (utente == null) {
                        System.out.println("Devi prima fare login!");
                        break;
                    }

                    if (utente instanceof Scienziato) {
                        System.out.println("\n1. Aggiungi esperimento");
                        System.out.println("2. Stampa tutti esperimenti (solo ScienziatoCapo)");
                        System.out.print("Scegli opzione: ");
                        int op = sc.nextInt();
                        sc.nextLine();

                        if (op == 1) {
                            System.out.print("Inserisci nome esperimento: ");
                            String exp = sc.nextLine();
                            ((Scienziato) utente).aggiungiEsperimento(exp);
                        } else if (op == 2) {
                            ((Scienziato) utente).stampaTuttiEsperimenti();
                        }

                    } else if (utente instanceof Ispettore) {
                        System.out.println("\n1. Inserisci valutazione");
                        System.out.println("2. Stampa tutte valutazioni (solo IspettoreEsperto)");
                        System.out.print("Scegli opzione: ");
                        int op = sc.nextInt();
                        sc.nextLine();

                        if (op == 1) {
                            System.out.print("Inserisci voto (1-5): ");
                            int voto = sc.nextInt();
                            sc.nextLine();
                            ((Ispettore) utente).aggiungiValutazione(voto);
                        } else if (op == 2) {
                            ((Ispettore) utente).stampaTutteValutazioni();
                        }
                    }
                    break;

                case 5: // USCITA
                    System.out.println("Missione terminata. Arrivederci!");
                    sc.close();
                    return;

                default:
                    System.out.println("Opzione non valida!");
            }
        }
    }
}