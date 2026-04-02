import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.setupDatabase(); // crea DB e tabella se non esistono
        Scanner scanner = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Aggiungi studente");
            System.out.println("2. Mostra studenti");
            System.out.println("3. Modifica voto");
            System.out.println("4. Cerca studente");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            scelta = scanner.nextInt();
            scanner.nextLine(); // pulizia buffer

            switch (scelta) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Voto: ");
                    int voto = scanner.nextInt();
                    scanner.nextLine();

                    Studente nuovo = new Studente(nome, voto);
                    nuovo.salvaDB();
                    System.out.println("Studente aggiunto.");
                    break;

                case 2:
                    ArrayList<Studente> lista = Studente.leggiTutti();
                    for (Studente st : lista) {
                        st.stampaStudente();
                    }
                    break;

                case 3:
                    System.out.print("Nome dello studente da modificare: ");
                    String nomeMod = scanner.nextLine();
                    Studente trovato = Studente.cercaStudente(nomeMod);

                    if (trovato != null) {
                        System.out.print("Nuovo voto: ");
                        int nuovoVoto = scanner.nextInt();
                        scanner.nextLine();
                        Studente.modificaVoto(nomeMod, nuovoVoto);
                        System.out.println("Voto aggiornato.");
                    } else {
                        System.out.println("Studente non trovato.");
                    }
                    break;

                case 4:
                    System.out.print("Nome da cercare: ");
                    String nomeRic = scanner.nextLine();
                    Studente ric = Studente.cercaStudente(nomeRic);

                    if (ric != null) {
                        ric.stampaStudente();
                    } else {
                        System.out.println("Studente non trovato.");
                    }
                    break;

                case 0:
                    System.out.println("Uscita");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);

        scanner.close();
    }
}