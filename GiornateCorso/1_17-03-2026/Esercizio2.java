import java.util.Scanner;
public class Esercizio2 {
    public static void main(String[] args) {
        System.out.println("inserire username");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        if (username == ""){ //condizioni
            System.out.println("Username non valido");
        } else if (username.length() < 5){
            System.out.println("username troppo corto");
        } else {
            System.out.println("Username valido");
        }
        scanner.close();
    }
    
}
