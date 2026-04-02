import java.util.Scanner;

public class EsercizioVotiConFunzioni {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double voti[] = null; // creiamo array voti
        boolean restanelprogramma = true; 

        final String PASSWORD_INSEGNANTE = "1234"; // password insegnante. final perchè una volta inserita non può essere cambiata (in questo caso)

        while (restanelprogramma) {
            // Stampo il menu per le operazioni
            System.out.println("\nMENU OPERAZIONI:");
            System.out.println("1 - Inserire voti");
            System.out.println("2 - Calcolare media");
            System.out.println("3 - Trova voto massimo");
            System.out.println("4 - Trova voto minimo");
            System.out.println("5 - Verifica promozione");
            System.out.println("6 - Inserisci/Modifica voto insegnante");
            System.out.println("7 - Esci");
            System.out.print("Scegli un'opzione: ");

            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    // Inserimento voti
                    voti = inserisciVoti(scanner);
                    break;
                case 2:
                    // Calcolo media se i voti sono stati inseriti
                    if (voti != null)
                        System.out.println("Media: " + calcolaMedia(voti));
                    else
                        System.out.println("Devi prima inserire i voti!");
                    break;
                case 3:
                    // Trova il voto massimo
                    if (voti != null)
                        System.out.println("Voto massimo: " + trovaMassimo(voti));
                    else
                        System.out.println("Devi prima inserire i voti!");
                    break;
                case 4:
                    // Trova il voto minimo
                    if (voti != null)
                        System.out.println("Voto minimo: " + trovaMinimo(voti));
                    else
                        System.out.println("Devi prima inserire i voti!");
                    break;
                case 5:
                    // Verifica promozione basata sulla media
                    if (voti != null) {
                        boolean promosso = verificaPromozione(calcolaMedia(voti));
                        if (promosso) {
                            System.out.println("Promosso");
                        } else {
                            System.out.println("Non promosso");
                        }
                    } else
                        System.out.println("Devi prima inserire i voti!");
                    break;
                case 6:
                    // Metodo insegnante con password
                    if (voti != null) {
                        System.out.print("Inserisci password insegnante: ");
                        String password = scanner.next();
                        if (password.equals(PASSWORD_INSEGNANTE)) {
                            // Password corretta, può modificare i voti
                            inserisciVotoInsegnante(scanner, voti);
                        } else {
                            // Password sbagliata, accesso bloccato
                            System.out.println("Password errata! Accesso negato.");
                        }
                    } else
                        System.out.println("Devi prima inserire i voti dello studente!");
                    break;
                case 7:
                    // Uscita dal programma
                    System.out.println("Uscita dal programma.");
                    restanelprogramma = false;
                    break;
                default:
                    // Opzione non valida
                    System.out.println("Scelta non valida. Riprova.");
            }
        }

        scanner.close();
    }

    // Metodo per inserire voti
    public static double[] inserisciVoti(Scanner scanner) {
        System.out.print("Quanti voti vuoi inserire? ");
        int numVoti = scanner.nextInt();
        double[] voti = new double[numVoti]; // creo array voti

        for (int i = 0; i < numVoti; i++) {
            double voto;
            do {
                System.out.print("Inserisci voto numero " + (i + 1) + ": "); // richiedo i voti uno alla volta
                voto = scanner.nextDouble();
                if (voto < 0) {
                    // Controllo che il voto non sia negativo
                    System.out.println("Il voto non può essere negativo. Riprova.");
                }
            } while (voto < 0);
            voti[i] = voto; // salvo il voto valido nell'array
        }

        return voti;
    }

    // Metodo insegnante per inserire o modificare un voto
    public static void inserisciVotoInsegnante(Scanner scanner, double[] voti) {
        System.out.print("Inserisci il numero del voto da modificare (1-" + voti.length + "): ");
        int index = scanner.nextInt() - 1;

        if (index < 0 || index >= voti.length) {
            // Controllo che il numero del voto da cambiare sia valido
            System.out.println("Numero non valido!");
            return;
        }

        double voto;
        do {
            System.out.print("L'insegnante inserisce il nuovo voto: ");
            voto = scanner.nextDouble();
            if (voto < 0) {
                // Controllo che il voto non sia negativo
                System.out.println("Il voto non può essere negativo. Riprova.");
            }
        } while (voto < 0);

        voti[index] = voto; // aggiorno il voto nell'array
        System.out.println("Voto aggiornato con successo dall'insegnante.");
    }

    // Metodo per calcolare la media
    public static double calcolaMedia(double[] voti) {
        double somma = 0;
        for (double v : voti) { // uso il foreach, più semplice
            somma += v;
        }
        return somma / voti.length;
    }

    // Metodo per trovare il voto massimo
    public static double trovaMassimo(double[] voti) {
        double max = voti[0];
        for (double v : voti) { // uso il foreach
            if (v > max) //se il voto inserito è superiore al voto massimo diventa il voto massimo
                max = v;
        }
        return max;
    }

    // Metodo per trovare il voto minimo
    public static double trovaMinimo(double[] voti) {
        double min = voti[0];
        for (double v : voti) { // uso il foreach
            if (v < min) //se il voto inserito è inferiore al voto minimo diventa il voto minimo
                min = v;
        }
        return min;
    }

    // Metodo per verificare promozione
    public static boolean verificaPromozione(double media) {
        // promosso se media >= 6
        return media >= 6.0;
    }
}