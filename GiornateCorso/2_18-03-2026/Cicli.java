import java.util.Scanner;

public class Cicli {
    public static void main(String[] args) {
        int x = 0; //inizializzo la variabile
        while(x < 10){ //finchè x è minore di 10 
            ++x; //pre incremento, il 10 viene stampato perchè è pre incremento
            System.out.println(x); //stampa a partire da 1
        }

        do{++x; // fa prima il pre incremento quindi parte da 1
            System.out.println(x);
        } while(x<10); // finchè x è minore di 10, ma quando x è uguale a 10 prima lo fa (do) poi controlla la condizione ed esce, infatti stampa 11


        Scanner scanner = new Scanner(System.in);
        boolean continua = true;
        while (continua) {
            System.out.println("inserisci numero, 0 per uscire");
            int numero = scanner.nextInt();
            if (numero == 0){
                continua = false; //se scrivi 0 esce dal while perchè continua diventa falso
                System.out.println("programma terminato");
            }else {
                System.out.println("Hai inserito " + numero);
                 
            }
        } scanner.close();

    }
}
