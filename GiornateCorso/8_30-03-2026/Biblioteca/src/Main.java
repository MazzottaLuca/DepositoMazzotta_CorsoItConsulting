
// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("");
            System.out.println("GESTIONE BIBLIOTECA");

            System.out.println("1. Mostra tutti i libri");
            System.out.println("2. Aggiungi libro");
            System.out.println("3. Cerca libro");
            System.out.println("4. Modifica libro");
            System.out.println("5. Elimina libro");
            System.out.println("6. Prendi in prestito");
            System.out.println("7. Restituisci libro");
            System.out.println("0. Esci");

            System.out.print("Scelta: ");
            scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> library.displayBooks();

                case 2 -> {
                    System.out.print("Titolo: ");
                    String title = scanner.nextLine();
                    System.out.print("Autore: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                }

                case 3 -> {
                    System.out.println("\n  1. Cerca per titolo");
                    System.out.println("  2. Cerca per autore");
                    System.out.println("  3. Cerca per disponibilità");
                    System.out.print("Scelta ricerca: ");
                    int tipoRicerca = scanner.nextInt();
                    scanner.nextLine();

                    switch (tipoRicerca) {
                        case 1 -> {
                            System.out.print("Titolo da cercare: ");
                            library.searchByTitle(scanner.nextLine());
                        }
                        case 2 -> {
                            System.out.print("Autore da cercare: ");
                            library.searchByAuthor(scanner.nextLine());
                        }
                        case 3 -> {
                            System.out.print("Disponibile? (s/n): ");
                            boolean disp = scanner.nextLine().equalsIgnoreCase("s");
                            library.searchByAvailability(disp);
                        }
                        default -> System.out.println("Scelta non valida.");
                    }
                }

                case 4 -> {
                    System.out.print("Titolo del libro da modificare: ");
                    String vecchioTitolo = scanner.nextLine();
                    System.out.print("Nuovo titolo (invio per non cambiare): ");
                    String nuovoTitolo = scanner.nextLine();
                    System.out.print("Nuovo autore (invio per non cambiare): ");
                    String nuovoAutore = scanner.nextLine();
                    library.updateBook(vecchioTitolo, nuovoTitolo, nuovoAutore);
                }

                case 5 -> {
                    System.out.print("Titolo del libro da eliminare: ");
                    library.deleteBook(scanner.nextLine());
                }

                case 6 -> {
                    System.out.print("Titolo da prendere in prestito: ");
                    library.borrowBook(scanner.nextLine());
                }

                case 7 -> {
                    System.out.print("Titolo da restituire: ");
                    library.returnBook(scanner.nextLine());
                }

                case 0 -> System.out.println("Arrivederci!");
                default -> System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);

        library.close();
        scanner.close();
    }
}