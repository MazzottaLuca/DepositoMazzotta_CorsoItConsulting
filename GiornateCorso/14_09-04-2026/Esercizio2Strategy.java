import java.util.*;

public class Esercizio2Strategy {
    // interfaccia strategy

    public interface MetodoPagamento {
        void paga(double importo);
    }

    // strategia carta di credito
    public static class CartaDiCredito implements MetodoPagamento {
        @Override
        public void paga(double importo) {
            System.out.println("stai pagando con carta di credito euro " + importo);
        }
    }

    // strategia paypal
    public static class Paypal implements MetodoPagamento {
        @Override
        public void paga(double importo) {
            System.out.println("stai pagando con paypal euro " + importo);
        }
    }

    // context
    public static class PagamentoContext {
        private MetodoPagamento metodo;

        public void setMetodo(MetodoPagamento metodo) {
            this.metodo = metodo;
        }

        public void eseguiPagamento(double importo) {
            if (metodo == null) {
                throw new IllegalArgumentException("nessun metodo di pagamento impostato");
            }
            metodo.paga(importo);
        }
    }

    // main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PagamentoContext context = new PagamentoContext();

        System.out.println("inserisci l'importo");
        double importo = scanner.nextDouble();
        System.out.println("scegli metodo di pagamento:\n 1-carta di credito \n 2-PayPal");
        int scelta = scanner.nextInt();
        if (scelta == 1) {
            context.setMetodo(new CartaDiCredito());
        } else if (scelta == 2) {
            context.setMetodo(new Paypal());
        } else {
            System.out.println("scelta non valida");
            scanner.close();
            return;
        }
        context.eseguiPagamento(importo);
        System.out.println("Inserisci pin");
        int pincorretto = 1234;
        int pin = scanner.nextInt();

        if (pin == pincorretto) {
            System.out.println("pagamento di " + importo + " euro effettuato correttamente");
            scanner.close();
        } else {
            System.out.println("pin errato, pagamento non andato a buon fine");
        }
    }
}
