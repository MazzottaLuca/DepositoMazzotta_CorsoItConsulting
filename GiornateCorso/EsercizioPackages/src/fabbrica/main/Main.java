package fabbrica.main;

import fabbrica.personale.Operaio;
import fabbrica.personale.OperaioSpecial;
import fabbrica.personale.OperaioDirigente;
import fabbrica.produzione.Macchina;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creazione oggetti base
        System.out.print("Inserisci il nome dell'Operaio: ");
        String nomeOperaio = scanner.nextLine();

        System.out.print("Inserisci il nome dell'OperaioSpecial: ");
        String nomeSpecial = scanner.nextLine();

        System.out.print("Inserisci il nome dell'OperaioDirigente: ");
        String nomeDirigente = scanner.nextLine();

        System.out.print("Inserisci il nome della Macchina: ");
        String nomeMacchina = scanner.nextLine();

        Operaio operaio = new Operaio(nomeOperaio);
        OperaioSpecial operaioSpecial = new OperaioSpecial(nomeSpecial);
        OperaioDirigente operaioDirigente = new OperaioDirigente(nomeDirigente);
        Macchina macchina = new Macchina(nomeMacchina);

        // Famiglie di prodotti disponibili
        String[] famigliaProdottiA = { "Bullone A1", "Vite A2", "Dado A3" };
        String[] famigliaProdottiB = { "Lamiera B1", "Trave B2", "Piastra B3" };

        boolean esci = false;

        while (!esci) {
            System.out.println("\n======= MENU FABBRICA =======");
            System.out.println("1. OperaioSpecial accende la macchina");
            System.out.println("2. Crea prodotto (famiglia A)");
            System.out.println("3. Crea prodotto (famiglia B)");
            System.out.println("4. OperaioDirigente stampa lo stato della macchina");
            System.out.println("5. Operaio spegne la macchina");
            System.out.println("6. Inserisci prodotto personalizzato");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            String scelta = scanner.nextLine().trim();

            switch (scelta) {
                case "1":
                    operaioSpecial.lavora(macchina);
                    break;

                case "2":
                    System.out.println("Prodotti famiglia A disponibili:");
                    for (int i = 0; i < famigliaProdottiA.length; i++) {
                        System.out.println("  " + (i + 1) + ". " + famigliaProdottiA[i]);
                    }
                    System.out.print("Scegli (1-" + famigliaProdottiA.length + "): ");
                    try {
                        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        if (idx >= 0 && idx < famigliaProdottiA.length) {
                            macchina.creaProdotto(famigliaProdottiA[idx]);
                        } else {
                            System.out.println("Scelta non valida.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input non valido.");
                    }
                    break;

                case "3":
                    System.out.println("Prodotti famiglia B disponibili:");
                    for (int i = 0; i < famigliaProdottiB.length; i++) {
                        System.out.println("  " + (i + 1) + ". " + famigliaProdottiB[i]);
                    }
                    System.out.print("Scegli (1-" + famigliaProdottiB.length + "): ");
                    try {
                        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        if (idx >= 0 && idx < famigliaProdottiB.length) {
                            macchina.creaProdotto(famigliaProdottiB[idx]);
                        } else {
                            System.out.println("Scelta non valida.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input non valido.");
                    }
                    break;

                case "4":
                    operaioDirigente.controllaMacchina(macchina);
                    break;

                case "5":
                    operaio.ferma(macchina);
                    break;

                case "6":
                    System.out.print("Inserisci il nome del prodotto: ");
                    String prodottoCustom = scanner.nextLine();
                    macchina.creaProdotto(prodottoCustom);
                    break;

                case "0":
                    esci = true;
                    System.out.println("Programma terminato.");
                    break;

                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }

        scanner.close();
    }
}