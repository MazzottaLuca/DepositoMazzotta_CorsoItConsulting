import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Negozio calzature = new Negozio("Calzature");
        Negozio vestiario = new Negozio("Vestiario");
        Negozio ottica = new Negozio("Ottica");

        int scelta;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Inserisci prodotto");
            System.out.println("2. Visualizza negozi");
            System.out.println("3. Negozio con prodotto più costoso");
            System.out.println("4. Vendi prodotto");
            System.out.println("5. Esci");
            System.out.print("Scelta: ");

            scelta = sc.nextInt();
            sc.nextLine();

            switch (scelta) {

                case 1:
                    System.out.println("Scegli negozio: 1-Calzature 2-Vestiario 3-Ottica");
                    int n = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nome prodotto: ");
                    String nome = sc.nextLine();

                    System.out.print("Prezzo: ");
                    double prezzo = sc.nextDouble();

                    System.out.print("Quantità: ");
                    int q = sc.nextInt();

                    Prodotto p = new Prodotto(nome, q, prezzo);

                    if (n == 1) calzature.setProdotto(p);
                    else if (n == 2) vestiario.setProdotto(p);
                    else if (n == 3) ottica.setProdotto(p);

                    break;

                case 2:
                    calzature.stampa();
                    vestiario.stampa();
                    ottica.stampa();
                    break;

                case 3:
                    Negozio max = null;

                    if (calzature.getProdotto() != null) max = calzature;
                    if (vestiario.getProdotto() != null &&
                        (max == null || vestiario.getProdotto().getPrezzo() > max.getProdotto().getPrezzo()))
                        max = vestiario;
                    if (ottica.getProdotto() != null &&
                        (max == null || ottica.getProdotto().getPrezzo() > max.getProdotto().getPrezzo()))
                        max = ottica;

                    if (max != null) {
                        System.out.println("Negozio con prodotto più costoso:");
                        max.stampa();
                    } else {
                        System.out.println("Nessun prodotto disponibile.");
                    }
                    break;

                case 4:
                    System.out.println("Scegli negozio: 1-Calzature 2-Vestiario 3-Ottica");
                    int sceltaNeg = sc.nextInt();

                    System.out.print("Quantità da vendere: ");
                    int quantitaVendita = sc.nextInt();

                    Negozio negozioScelto = null;

                    if (sceltaNeg == 1) negozioScelto = calzature;
                    else if (sceltaNeg == 2) negozioScelto = vestiario;
                    else if (sceltaNeg == 3) negozioScelto = ottica;

                    if (negozioScelto != null && negozioScelto.getProdotto() != null) {
                        negozioScelto.getProdotto().vendi(quantitaVendita);
                    } else {
                        System.out.println("Nessun prodotto nel negozio.");
                    }
                    break;

                case 5:
                    System.out.println("Uscita...");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 5);

        sc.close();
    }
}