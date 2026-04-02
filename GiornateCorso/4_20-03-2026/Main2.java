import java.util.Scanner;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Auto> listaAuto = new ArrayList<>();
        int scelta = 0;
        while (scelta != 5) {
            System.out.println("1- inserisci dati");
            System.out.println("2- visualizza");
            System.out.println("3- modifica prezzo");
            System.out.println("4- verifica età");
            System.out.println("5- esci");
            scelta = scanner.nextInt();
            scanner.nextLine();
            switch (scelta) {
                case 1:
                    // Creazione auto
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();

                    System.out.print("Modello: ");
                    String modello = scanner.nextLine();

                    System.out.print("Anno: ");
                    int anno = Integer.parseInt(scanner.nextLine());
                    if (anno > 2026 || anno < 0){
                        System.out.println("Inserire anno valido");
                        break;
                    }

                    System.out.print("Prezzo: ");
                    double prezzo = Double.parseDouble(scanner.nextLine());
                    listaAuto.add(new Auto(marca, modello, anno, prezzo));
                    System.out.println("Auto aggiunta con successo!");
                    break;
                case 2:
                    // visualizzazione auto
                    if (listaAuto.isEmpty()) {
                        System.out.println("non ci sono auto");
                    } else { // get di auto, faccio un ciclo for per ottenere tutte le auto
                        int contatore = 1;
                        for (Auto a : listaAuto) {
                            System.out.println(
                                    contatore + " " + a.marca + " " + a.modello + " " + a.anno + " " + a.prezzo);
                            contatore++;
                        }
                    }
                    break;
                case 3:
                    // modifica prezzo
                    if (listaAuto.isEmpty()) { // sempre questa condizione ma in questo caso break perchè se non ci sono
                                               // auto non posso modificare un'auto
                        System.out.println("non ci sono auto");
                        break;
                    } else {
                        int seleziona = scanner.nextInt() - 1;// IL MENO 1 PERCHE' L'ARRAY PARTE DA INDICE ZERO
                        if (seleziona >= 0 && seleziona < listaAuto.size()) {
                            System.out.print("Nuovo prezzo: ");
                            double nuovoPrezzo = scanner.nextDouble();
                            listaAuto.get(seleziona).prezzo = nuovoPrezzo;
                            System.out.println("Prezzo aggiornato!");
                        } else {
                            System.out.println("Indice non valido.");
                        }
                        scanner.nextLine();
                        break;
                    }
                case 4:
                    // Verifica età auto
                    if (listaAuto.isEmpty()) {
                        System.out.println("Nessuna auto registrata.");
                        break;
                    }
                    System.out.print("Seleziona il numero dell'auto da verificare: ");
                    int altrocontatore = scanner.nextInt() - 1;
                    if (altrocontatore >= 0 && altrocontatore < listaAuto.size()) {
                        Auto a = listaAuto.get(altrocontatore);
                        int eta = 2026 - a.anno; // anno corrente meno l'anno di uscita "anno"
                        System.out.println("L'auto " + a.marca + " " + a.modello + " ha " + eta + " anni. ");
                        if (eta < 5) {
                            System.out.println("nuova.");
                        } else if (eta <= 15) {
                            System.out.println("usata.");
                        } else {
                            System.out.println("vecchia.");
                        }
                    } else {
                        System.out.println("Indice non valido.");
                    }
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println("Uscita in corso...");
                    break;

                default:
                    System.out.println("Opzione non valida. Riprova.");

            }

        }
        scanner.close();
    }
}