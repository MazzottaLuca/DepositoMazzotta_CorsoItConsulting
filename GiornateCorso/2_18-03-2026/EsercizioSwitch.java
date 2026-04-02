import java.util.Scanner;

//import java.util.InputMismatchException;
public class EsercizioSwitch {
    public static void main(String[] args) {
        // try { //gestione errori del distributore, in questo caso funziona sempre
        Scanner inserisciCredito = new Scanner(System.in); // inserisco il credito prima di scegliere se uscire,
                                                           // altrimenti lo scanner lo metto nel while
        System.out.println("Inserisci credito:");
        double credito = inserisciCredito.nextDouble();
        double nuovocredito = credito;

        int scelta = 0;
        while (nuovocredito > 0.00 && scelta != 5) { // finchè il credito è superiore a 0 o la scelta non è 5 resta nel
                                                     // programma

            System.out.println("\nHai euro " + nuovocredito); // ogni volta
            System.out.println(
                    "1- caffe euro 1.50  2- cappuccino euro 2.00  3- te euro 1.00  4- acqua euro 0.50  5- esci");
            System.out.print("Fai una scelta: ");
            scelta = inserisciCredito.nextInt();

            switch (scelta) {
                case 1:
                    if (nuovocredito >= 1.50) {
                        nuovocredito -= 1.50;
                        System.out.println("Hai preso il caffe al prezzo di euro 1.50");
                        System.out.println("Credito rimanente " + nuovocredito + " euro"); // per ogni tasto
                                                                                           // selezionato
                    } else {
                        System.out.println("Credito insufficiente!");
                    }
                    break;
                case 2:
                    if (nuovocredito >= 2.00) {
                        nuovocredito -= 2.00;
                        System.out.println("Hai preso il cappuccino al prezzo di euro 2.00");
                        System.out.println("Credito rimanente " + nuovocredito + " euro");
                    } else {
                        System.out.println("Credito insufficiente!");
                    }
                    break;
                case 3:
                    if (nuovocredito >= 1.00) {
                        nuovocredito -= 1.00;
                        System.out.println("Hai preso il te al prezzo di euro 1.00");
                        System.out.println("Credito rimanente " + nuovocredito + " euro");
                    } else {
                        System.out.println("Credito insufficiente!");
                    }
                    break;
                case 4:
                    if (nuovocredito >= 0.50) {
                        nuovocredito -= 0.50;
                        System.out.println("Hai preso l'acqua al prezzo di euro 0.50");
                        System.out.println("Credito rimanente " + nuovocredito + " euro");
                    } else {
                        System.out.println("Credito insufficiente!");
                    }
                    break;
                case 5:
                    System.out.println("Arrivederci");
                    System.out.println("Credito rimanente " + nuovocredito + " euro");
                    break;
                default:
                    System.out.println("Inserisci un numero corretto");
            }
        }
        System.out.println("Saldo finale: " + nuovocredito + " euro");
        inserisciCredito.close();
    }

    /*
     * catch (InputMismatchException e) {
     * System.out.println("Distributore non funzionante");
     * System.out.println("Scelta non valida");
     * }
     * }
     */
}