import java.util.ArrayList;
import java.util.Scanner;

public class EsercizioArrayList2 {
    public static void main(String[] args) {
        int scelta = 0;
        ArrayList<String> nomi = new ArrayList<>();
        Scanner opzione = new Scanner(System.in);
        while (scelta != 5) {
            System.out.println(
                    "Selezionare numero: \n 1-Aggiungi nome \n 2-Visualizza tutti i nomi \n 3-Cerca nome \n 4-Rimuovi nome \n 5-esci");
            scelta = opzione.nextInt();
            opzione.nextLine();
            switch (scelta) {
                case 1:
                    System.out.println("inserisci nome: ");
                    String nome = opzione.nextLine();
                    nomi.add(nome);
                    break;
                case 2:
                    System.out.println("Lista nomi: ");
                    if (nomi.isEmpty()) {
                        System.out.println("Lista vuota");
                    }
                    for (String n : nomi) {
                        System.out.println(n);
                    }
                    break;
                case 3:
                    System.out.println("Inserisci il nome da cercare: ");
                    String cerca = opzione.nextLine();
                    if (nomi.contains(cerca)) {
                        System.out.println("il nome " + cerca + " esiste");
                    } else {
                        System.out.println("nome non trovato");
                    }
                    break;
                case 4:
                    System.out.println("Inserisci il nome da rimuovere: ");
                    String rimuovi = opzione.nextLine();
                    if (nomi.contains(rimuovi)) {
                        nomi.remove(rimuovi);
                        System.out.println("nome rimosso");
                    } else {
                        System.out.println("Nome non trovato o già rimosso");
                    }
                    break;
                case 5:
                    System.out.println("Sei uscito");
                    break;
                default:
                    System.out.println("inserisci una opzione valida");
            }
        }
        opzione.close();
    }
}
