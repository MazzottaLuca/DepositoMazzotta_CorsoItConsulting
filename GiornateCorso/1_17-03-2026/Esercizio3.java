import java.util.Scanner;

public class Esercizio3 {
    public static void main(String[] args) {
        String username = "admin";
        String password = "1234";
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserire username");
        String usernameverifica = scanner.nextLine();
        System.out.println("inserire password");
        String passwordverifica = scanner.nextLine();
        if (usernameverifica.equals(username) && passwordverifica.equals(password)) {
            System.out.println("accesso consentito");
        
        } else if (!usernameverifica.equals(username) || !passwordverifica.equals(password)) {
            System.out.println("credenziali errate");

        } else
            System.out.println("accesso negato");
        scanner.close();
    }
}
