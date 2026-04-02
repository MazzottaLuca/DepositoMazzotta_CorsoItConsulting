import java.util.Scanner;
public class EsercizioCicloFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserire un numero: /n");
        int numero = scanner.nextInt();
        for (int i = 1; i <= 10; i++){
            int prodotto = i* numero;
            System.out.println(prodotto);
            i++;
        }
        scanner.close();
    }   
}
