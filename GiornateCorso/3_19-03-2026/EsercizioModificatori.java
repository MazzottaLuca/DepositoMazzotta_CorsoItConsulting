import java.util.Scanner;
public class EsercizioModificatori {
    static void multiply(int a, int b){
        int somma = a + b;
        System.out.println("Somma interi= " + somma);
    }
    static void multiply(double c, double d, double e){
        double somma = c + d + e;
        System.out.println("Somma double = " + somma);
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scegli quale somma fare, scrivi 1 per fare la somma tra due interi, scrvi 2 per fare la somma tra tre double");
        int scelta = scanner.nextInt();
        if (scelta == 1){
            System.out.println("inserisci primo numero");
            int n1 = scanner.nextInt();
            System.out.println("inserisci secondo numero");
            int n2 = scanner.nextInt();
            multiply(n1, n2);
        } else if (scelta == 2){
            System.out.println("inserisci primo numero");
            double n1 = scanner.nextDouble();
            System.out.println("inserisci secondo numero");
            double n2 = scanner.nextDouble();
            System.out.println("inserisci terzo numero");
            int n3 = scanner.nextInt();
            multiply(n1, n2, n3);
        } else {
            System.out.println("scelta non valida");
        }
        scanner.close();
    }
}
