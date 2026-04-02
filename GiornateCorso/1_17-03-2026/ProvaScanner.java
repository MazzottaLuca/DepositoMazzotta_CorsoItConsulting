import java.util.Scanner;
public class ProvaScanner {
    public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in); //chiamo lo scanner
    System.out.println("Scrivi il tuo nome");
    String username = scanner.nextLine(); //input stringa
    System.out.println("Scrivi la tua età");
    int eta = scanner.nextInt(); //input intero
    System.out.println("Benvenuto " + username + " di anni " + eta); 
    scanner.close();  
    }
    
}
