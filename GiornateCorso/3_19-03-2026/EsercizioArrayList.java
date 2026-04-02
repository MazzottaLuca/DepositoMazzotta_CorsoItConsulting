import java.util.ArrayList;
import java.util.Scanner;

public class EsercizioArrayList {
    public static void main(String[] args) {
        ArrayList<String> studenti = new ArrayList<>(); // creo arraylist di stringhe
        ArrayList<Integer> eta = new ArrayList<>(); // creo arraylist di interi
        ArrayList<Float> voti = new ArrayList<>(); // creo arraylist di float

        Scanner inseriscinome = new Scanner(System.in); // uso uno scanner solo (sconsigliato)
        while (true) { // mantiene attivo il programma
            System.out.println("inserisci nomi:");
            String nomi = inseriscinome.nextLine();

            if (nomi.equalsIgnoreCase("fine")) { // anche se lo scrivo maiuscolo
                break; // quando scrivo fine interrompe il programma
            }
            System.out.println("Inserisci eta:");
            int anni = inseriscinome.nextInt();
            System.out.println("Inserisci voto:");
            float voto = inseriscinome.nextFloat();
            inseriscinome.nextLine();
            // inserimento nella lista
            studenti.add(nomi);
            eta.add(anni);
            voti.add(voto);
        }
        inseriscinome.close();
        for (int i = 0; i < studenti.size() - 1; i++) {
            for (int j = 0; j < studenti.size() - 1 - i; j++) {
                if (studenti.get(j).compareToIgnoreCase(studenti.get(j + 1)) > 0) {
                    String ordina = studenti.get(j); // così si salva il primo studente
                    studenti.set(j, studenti.get(j + 1)); // il secondo viene messo al posto del primo e viene fatta la
                                                          // comparazione
                    studenti.set(j + 1, ordina); // salva il primo al posto del secondo

                    // aggiungo ordinamento eta
                    int ordinaeta = eta.get(j);
                    eta.set(j, eta.get(j + 1));
                    eta.set(j + 1, ordinaeta);

                    // aggiungo ordinamento voti
                    Float ordinavoti = voti.get(j);
                    voti.set(j, voti.get(j + 1));
                    voti.set(j + 1, ordinavoti);
                }
            }
        }

        System.out.println("Lista studenti " + studenti);
        for (int k = 0; k < studenti.size(); k++) {
            System.out.println(
                    "Nome: " + studenti.get(k) + "\n" +
                            "Anni: " + eta.get(k) + "\n" +
                            "Voto: " + voti.get(k));
        }
    }
}
