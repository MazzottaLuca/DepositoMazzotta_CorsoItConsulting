import java.util.Scanner;
public class EsercizioSommaRicorsiva {
    static int somma(int n){
        if (n == 1){
            return 1;
        } else {
            return n + somma(n - 1); //questa è la ricorsiva, si fa sempre finchè n non è 1
        }
    }   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci un numero naturale:");
        int n = scanner.nextInt();
        if (n < 1) {
            System.out.println("Inserisci un numero naturale maggiore di zero!");
        } else {
            int risultato = somma(n);
            System.out.println("somma dei numeri da 1 a " + n + " = " + risultato);
        }

        scanner.close();
    }
}
