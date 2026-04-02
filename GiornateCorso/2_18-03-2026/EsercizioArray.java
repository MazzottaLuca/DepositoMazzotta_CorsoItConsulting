import java.util.Scanner;

public class EsercizioArray {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Quanti dolci vuoi ordinare?");

        int numerodolci = scanner.nextInt(); // numero di dolci
        if (numerodolci <= 10) {
            scanner.nextLine();

            String[] nomidolci = new String[numerodolci]; // Array che contiene i nomi dei dolci
            int[] quantita = new int[numerodolci]; // Array che contiene le quantità dei dolci

            for (int i = 0; i < numerodolci; i++) { // continua finchè la tipologia di dolci è uguale a numerodolci
                System.out.println("Inserisci il nome del dolce:");
                nomidolci[i] = scanner.nextLine();

                System.out.println("Inserisci la quantita:");
                if (scanner.hasNextInt()) {
                    quantita[i] = scanner.nextInt();

                    if (quantita[i] >= 0) {
                        scanner.nextLine();
                    } else {
                        System.out.println("Errore: quantità non valida. Programma interrotto.");
                        scanner.close();
                        return;
                    }

                } else {
                    System.out.println("Errore: quantità non valida. Programma interrotto.");
                    scanner.close();
                    return;
                }
                // per ogni dolce chiede prima il nome e poi la quantità
            }

            // stampa ordine
            System.out.println("\nHai ordinato:");
            for (int i = 0; i < numerodolci; i++) { // stampa dei dolci inseriti
                System.out.println(nomidolci[i] + " - quantita " + quantita[i]);
            }

            scanner.close();
        } else if (numerodolci > 10) {
            System.out.println("non puoi ordinare più di 10 tipi di dolce");
        }
    }
}