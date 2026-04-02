import java.util.Scanner;

public class EsercizioArrayMultidimensionali {
    public static void main(String[] args) {
        Scanner opzione = new Scanner(System.in);

        int maxNomi = 5; // numero massimo di nomi
        String[][] nomi = new String[maxNomi][1]; // array 2D: una colonna per il nome
        int contatore = 0; // gestisco manualmente il contatore perchè l'array ha dimensione fissa

        int scelta = 0;

        while (scelta != 5) {

            System.out.println(
                    "Selezionare numero: \n 1-Aggiungi nome \n 2-Visualizza tutti i nomi \n 3-Cerca nome \n 4-Rimuovi nome \n 5-esci");

            scelta = opzione.nextInt();
            opzione.nextLine();

            switch (scelta) {
                case 1:
                    if (contatore >= maxNomi) {
                        System.out.println("Limite massimo raggiunto."); // se supero 5 persone l'opzione 1 non funziona
                                                                         // più, a meno che non rimuovo il nome
                        break;
                    }
                    System.out.print("Inserisci nome: ");
                    String nome = opzione.nextLine();
                    nomi[contatore][0] = nome;
                    contatore++;
                    System.out.println("Nome inserito");
                    break;

                case 2:
                    System.out.println("Lista nomi:");
                    for (int i = 0; i < contatore; i++) {
                        System.out.println((i + 1) + ". " + nomi[i][0]);
                    }
                    break;

                case 3:
                    System.out.print("Inserisci il nome da cercare: ");
                    String cerca = opzione.nextLine();
                    boolean trovato = false;
                    for (int i = 0; i < contatore; i++) {
                        if (nomi[i][0].equalsIgnoreCase(cerca)) {
                            System.out.println("Trovato: " + nomi[i][0]);
                            trovato = true;
                        }
                    }
                    if (!trovato) {
                        System.out.println("Nome non trovato");
                    }
                    break;

                case 4:
                    System.out.print("Inserisci il nome da rimuovere: ");
                    String rimuovi = opzione.nextLine();
                    boolean rimosso = false;
                    for (int i = 0; i < contatore; i++) {
                        if (nomi[i][0].equalsIgnoreCase(rimuovi)) { // rimuovo anche se sbaglio a scrivere il maiuscolo
                                                                    // o minuscolo, da capire se va lasciato così
                            // Sposta tutti i nomi successivi indietro di una posizione
                            for (int j = i; j < contatore - 1; j++) {
                                nomi[j][0] = nomi[j + 1][0];
                            }
                            nomi[contatore - 1][0] = null;
                            contatore--;
                            rimosso = true;
                            System.out.println("Nome rimosso");
                            break;
                        }
                    }
                    if (!rimosso) {
                        System.out.println("Nome non trovato");
                    }
                    break;

                case 5:
                    System.out.println("Sei uscito");
                    break;

                default:
                    System.out.println("Inserisci una opzione valida");
            }
        }

        opzione.close();
    }
}