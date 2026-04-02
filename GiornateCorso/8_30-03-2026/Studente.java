import java.util.ArrayList;
import java.util.Scanner;

public class Studente {
    private static int id = 1; // id autoincrementale
    private int numero;         // numero effettivo dello studente
    private String nome;
    private int voto;

    // Costruttore: assegna automaticamente l'id
    public Studente(String nome, int voto) {
        this.numero = id++;
        this.nome = nome;
        this.voto = voto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        if (voto >= 0 && voto <= 10) {
            this.voto = voto;
        } else {
            System.out.println("Voto non valido");
        }
    }

    public void stampaStudente() {
        System.out.println("ID: " + numero + " Nome: " + nome + " Voto: " + voto);
    }

    public static Studente cercaStudente(ArrayList<Studente> listastudenti, String nome) {
        for (Studente s : listastudenti) {
            if (s.getNome().equalsIgnoreCase(nome)) {
                return s;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Studente> listastudenti = new ArrayList<>();
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
                    listastudenti.add(nuovo);

                    System.out.println("Studente aggiunto!");
                    break;

                case 2:
                    System.out.println("\nLista studenti:");
                    for (Studente s : listastudenti) {
                        s.stampaStudente();
                    }
                    break;

                case 3:
                    System.out.print("Nome studente da modificare: ");
                    String nomeMod = scanner.nextLine();

                    Studente trovato = cercaStudente(listastudenti, nomeMod);

                    if (trovato != null) {
                        System.out.print("Nuovo voto: ");
                        int nuovoVoto = scanner.nextInt();
                        scanner.nextLine();
                        trovato.setVoto(nuovoVoto);
                    } else {
                        System.out.println("Studente non trovato");
                    }
                    break;

                case 4:
                    System.out.print("Nome da cercare: ");
                    String nomeRic = scanner.nextLine();

                    Studente s = cercaStudente(listastudenti, nomeRic);

                    if (s != null) {
                        s.stampaStudente();
                    } else {
                        System.out.println("Studente non trovato");
                    }
                    break;

                case 0:
                    System.out.println("Uscita...");
                    break;

                default:
                    System.out.println("Scelta non valida");
            }

        } while (scelta != 0);

        scanner.close();
    }
}