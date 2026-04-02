import java.util.Scanner;

class Car extends Vehicle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Car myCar = new Car();

        // Input dati per la classe padre
        System.out.print("Inserisci numero ruote: ");
        myCar.numeroruote = sc.nextInt();
        sc.nextLine(); // pulizia buffer

        System.out.print("Inserisci marca: ");
        myCar.marca = sc.nextLine();

        System.out.print("Inserisci targa: ");
        myCar.targa = sc.nextLine();

        // Stampa dati
        myCar.costruisciveicolo();

        sc.close();
    }
}