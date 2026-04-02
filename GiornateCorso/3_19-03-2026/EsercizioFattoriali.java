import java.util.Scanner;

public class EsercizioFattoriali {
    //creo un metodo che prenda come parametro lo scanner che costruisco nel main
    public int numero(Scanner inserisci) {
        int n;
        do {
            System.out.print("Inserisci un numero intero positivo: ");
            n = inserisci.nextInt();
            if (n < 0) {
                System.out.println("Errore! Deve essere un numero positivo.");
            }
        } while (n < 0);
        return n;
    }

    public long fattoriale(int n) {
        long f = 1;
        for (int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    public static void main(String[] args) {
        Scanner inserisci = new Scanner(System.in); // Creo lo scanner una sola volta
        EsercizioFattoriali e = new EsercizioFattoriali();

        int n = e.numero(inserisci);
        long f = e.fattoriale(n);
        System.out.println("Fattoriale di " + n + " = " + f);

        inserisci.close(); // Lo chiudo solo alla fine del main
    }
}