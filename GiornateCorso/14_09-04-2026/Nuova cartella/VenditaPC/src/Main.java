import facade.SistemaOrdineFacade;
import java.util.Scanner;
import strategies.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // La Facade nasconde tutta la complessità degli altri pattern
        SistemaOrdineFacade negozio = new SistemaOrdineFacade();

        System.out.println("=== BENVENUTO NEL SISTEMA VENDITAPC ===");

        // 1. SCELTA CONFIGURAZIONE BASE
        System.out.println("\nSeleziona la base del PC:");
        System.out.println("1. PC Ufficio (500€)");
        System.out.println("2. PC Gaming (1200€)");
        int sceltaBase = scanner.nextInt();

        if (sceltaBase == 2) {
            negozio.selezionaBase("gaming");
        } else if (sceltaBase == 1) {
            negozio.selezionaBase("ufficio");
        } else
            System.out.println("inserisci opzione corretta");

        // 2. AGGIUNTA COMPONENTI EXTRA
        boolean continua = true;
        while (continua) {
            System.out.println("\nCosa vuoi aggiungere?");
            System.out.println("1. RAM Extra (+80€)");
            System.out.println("2. SSD Extra (+100€)");
            System.out.println("3. GPU Potenziata (+600€)");
            System.out.println("0. Vai al pagamento");

            int sceltaExtra = scanner.nextInt();

            switch (sceltaExtra) {
                case 1 -> negozio.aggiungiRam();
                case 2 -> negozio.aggiungiSsd();
                case 3 -> negozio.aggiungiGpu();
                case 0 -> continua = false;
                default -> System.out.println("Opzione non valida!");
            }
        }

        // 3. SCELTA STRATEGIA DI PAGAMENTO
        System.out.println("\nSeleziona il metodo di pagamento:");
        System.out.println("1. Carta di Credito");
        System.out.println("2. PayPal");
        int sceltaPagamento = scanner.nextInt();

        if (sceltaPagamento == 2) {
            negozio.scegliMetodoPagamento(new PagamentoPaypal());
        } else if (sceltaPagamento == 1) {
            negozio.scegliMetodoPagamento(new PagamentoCarta());
        } else
            System.out.println("inserisci opzione corretta");

        // 4. CONCLUSIONE TRAMITE FACADE
        negozio.completaOrdine();

        scanner.close();
        System.out.println("\nGrazie per l'acquisto!");
    }
}