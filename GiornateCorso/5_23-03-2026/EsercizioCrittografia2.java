import java.util.Scanner;

public class EsercizioCrittografia2 {

    // Metodo per cifrare
    public static String cifra(String testo, int shift) {
        testo = testo.trim(); //rimuove gli spazi, evita che io faccia la condizione c != ' ' ||
        String risultato = "";

        for (int i = 0; i < testo.length(); i++) {
            char c = testo.charAt(i);

            if (Character.isLetter(c)) { // se non è uno spazio ed è una lettera
                c = (char) (c + shift); // fai l'aumento
            }

            risultato = risultato + c;
        }

        return risultato;
    }

    public static String decifra(String testo, int shift) {
        testo = testo.trim();
        String risultato = "";

        for (int i = 0; i < testo.length(); i++) {
            char c = testo.charAt(i);

            if (Character.isLetter(c)) { // se non è uno spazio ed è una lettera
                c = (char) (c - shift); // fai la riduzione
            }

            risultato = risultato + c;
        }

        return risultato;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input utente
        System.out.print("Inserisci una frase: ");
        String parola = input.nextLine();

        System.out.println("1 - Cifra");
        System.out.println("2 - Decifra");
        int scelta = input.nextInt();
        System.out.print("Di quanto vuoi spostare i caratteri? ");
        int shift = input.nextInt();

        // Scelta operazione
        if (scelta == 1) {
            System.out.println("Risultato: " + cifra(parola, shift)); // fa la crittografia, prende ogni carattere e va
                                                                      // avanti di shift posti
        } else if (scelta == 2) {
            System.out.println("Risultato: " + decifra(parola, shift)); // ritorna la parola iniziale, ovviamente per
                                                                        // farlo si prende ogni carattere della parola
                                                                        // criptata e si torna indietro di shift posti
        } else {
            System.out.println("Scelta non valida");
        }

        input.close();
    }
}