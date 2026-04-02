import java.util.Scanner;
import java.time.LocalDate;

public class EsercizioSettimana {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("inserisci nome: ");
        String nome = scanner.nextLine();

        System.out.println("Inserisci anno di nascita: ");
        int anno = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci il giorno della settimana");
        String giorno = scanner.nextLine().toLowerCase();
        int giorniMancanti = 0;
        boolean weekend = false;
        switch (giorno) {
            case "lunedì": // per ogni giorno due case perchè così accetta sia lunedì con accento sia
            case "lunedi": // lunedi senza accento
                giorniMancanti = 5;
                break;
            case "martedì":
            case "martedi":
                giorniMancanti = 4;
                break;
            case "mercoledì":
            case "mercoledi":
                giorniMancanti = 3;
                break;
            case "giovedì":
            case "giovedi":
                giorniMancanti = 2;
                break;
            case "venerdì":
            case "venerdi":
                giorniMancanti = 1;
                break;
            case "sabato":
            case "domenica":
                weekend = true;
                break;
            default:
                System.out.println("Giorno non valido!");
                return;
        }

        int eta = LocalDate.now().getYear() - anno;

        if (weekend) {
            System.out.println("Il tuo nome è " + nome + ", hai " + eta + " anni ed è già weekend!");
        } else {
            System.out.println("Il tuo nome è " + nome + ", hai " + eta +
                    " anni e mancano " + giorniMancanti + " giorni al weekend");
        }

        scanner.close();
    }
}
