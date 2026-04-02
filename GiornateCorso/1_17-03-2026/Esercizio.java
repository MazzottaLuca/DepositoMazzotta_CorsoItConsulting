import java.util.Scanner;
public class Esercizio {
    public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
    System.out.println("Inserisci primo numero");
    int x = scanner.nextInt();
    System.out.println("Inserisci secondo numero");
    int y = scanner.nextInt();
    int somma = x + y;
    System.out.println("somma = " + somma);
    int differenza = x - y;
    System.out.println("differenza = " + differenza);
    int differenzaAssoluta = Math.abs(x-y);
    System.out.println("differenza assoluta = " + differenzaAssoluta);
    int prodotto = x * y;
    System.out.println("prodotto = " + prodotto);
    double potenza = Math.pow(x, y);
    System.out.println("potenza = " + potenza);
    scanner.close();
}
}
