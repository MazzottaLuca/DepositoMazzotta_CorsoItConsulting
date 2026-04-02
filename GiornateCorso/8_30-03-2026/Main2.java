import java.util.ArrayList;
import java.util.Scanner;

class Dipendente {
    String nome;
    double stipendio;
    String dipartimento;

    public Dipendente(String nome, double stipendio, String dipartimento) {
        this.nome = nome;
        this.stipendio = stipendio;
        this.dipartimento = dipartimento;
    }
}

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Dipendente> dipendenti = new ArrayList<>();

        boolean resta = true;

        while (resta) {
            System.out.println("\n--- MENU ---");
            System.out.println("1- Mostra dipendenti");
            System.out.println("2- Aggiungi dipendente");
            System.out.println("3- Modifica stipendio");
            System.out.println("4- Elimina dipendente");
            System.out.println("5- Esci");
            System.out.print("Scelta: ");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // pulizia buffer

            switch (scelta) {
                case 1:
                    if (dipendenti.isEmpty()) {
                        System.out.println("Non ci sono dipendenti.");
                    } else {
                        for (Dipendente d : dipendenti) {
                            System.out.println("\nNome: " + d.nome);
                            System.out.println("Stipendio: " + d.stipendio);
                            System.out.println("Dipartimento: " + d.dipartimento);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Stipendio: ");
                    double stipendio = scanner.nextDouble();
                    scanner.nextLine(); // pulizia buffer

                    System.out.print("Dipartimento: ");
                    String dipartimento = scanner.nextLine();

                    dipendenti.add(new Dipendente(nome, stipendio, dipartimento));
                    System.out.println("Dipendente aggiunto.");
                    break;

                case 3:
                    if (dipendenti.isEmpty()) {
                        System.out.println("Non ci sono dipendenti.");
                        break;
                    }

                    System.out.print("Nome dipendente: ");
                    String nomem = scanner.nextLine();
                    boolean trovato = false;

                    for (Dipendente d : dipendenti) {
                        if (d.nome.equalsIgnoreCase(nomem)) {
                            System.out.print("Nuovo stipendio: ");
                            double nuovoStipendio = scanner.nextDouble();
                            scanner.nextLine(); // pulizia buffer

                            d.stipendio = nuovoStipendio;
                            System.out.println("Stipendio aggiornato.");
                            trovato = true;
                            break;
                        }
                    }

                    if (!trovato) {
                        System.out.println("Dipendente non trovato.");
                    }
                    break;

                case 4:
                    if (dipendenti.isEmpty()) {
                        System.out.println("Non ci sono dipendenti.");
                        break;
                    }

                    System.out.print("Nome dipendente da eliminare: ");
                    String nomed = scanner.nextLine();
                    boolean eliminato = false;

                    for (int i = 0; i < dipendenti.size(); i++) {
                        if (dipendenti.get(i).nome.equalsIgnoreCase(nomed)) {
                            dipendenti.remove(i);
                            System.out.println("Dipendente eliminato.");
                            eliminato = true;
                            break;
                        }
                    }

                    if (!eliminato) {
                        System.out.println("Dipendente non trovato.");
                    }
                    break;

                case 5:
                    System.out.println("Uscita dal programma.");
                    resta = false;
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }
        }

        scanner.close();
    }
}