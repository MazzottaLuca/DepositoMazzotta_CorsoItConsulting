import java.util.Scanner;

public class EsercizioCrittografia {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Chiedo la parola all'utente
        System.out.print("Inserisci una parola: ");
        String parola = input.nextLine().trim(); // rimuove spazi iniziali e finali
        System.out.print("Inserisci numero spostamenti: ");
        int numerospostamenti = input.nextInt();
        String risultato = "";

        // Scorro ogni carattere della parola
        for (int i = 0; i < parola.length(); i++) { // faccio un ciclo per prendere ogni carattere della parola
            char c = parola.charAt(i);

            // Sposto il carattere di (numerospostamenti) posizioni
            c = (char) (c + numerospostamenti); // ogni carattere viene spostato di (numerospostamenti) posizioni

            // Aggiungo al risultato
            risultato = risultato + c;
        }

        // Stampo il risultato
        System.out.println("Parola cifrata: " + risultato);

        input.close();
    }
}
