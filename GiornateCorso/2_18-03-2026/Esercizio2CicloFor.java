import java.util.Scanner;

public class Esercizio2CicloFor {
    public static void main(String[] args) {
        System.out.println("inserisci un numero intero positivo");
        Scanner scanner = new Scanner(System.in);
        int quantita = scanner.nextInt();
        int somma = 0;
        int count = 0;
        for (int i = 1; i <= quantita; i++) {
            System.out.println("inserisci voto tra 0 e 30");
            int voto = scanner.nextInt();
            if (voto >= 18 && voto <= 24) {
                somma += voto;
                count++;
                System.out.println("sufficiente");
            } else if (voto > 24) {
                somma += voto;
                System.out.println("ottimo");
                count++;
            } else {System.out.println("voto insufficiente o non valido, non fa media");}
        }
        if (count > 0) {
            double media = (double) somma / count;
            System.out.println("Media voti " + media);
        } else {
            System.out.println("nessun voto inserito positivo");
        }
        scanner.close();
    }
}
