package strategies;
/**
 * CONCRETE STRATEGY
 * Implementa una logica specifica di pagamento (PayPal).
 */
public class PagamentoPaypal implements StrategiaPagamento {
    @Override
    public void eseguiPagamento(double importo) {
        System.out.println("Accesso a PayPal eseguito. Pagati " + importo + "euro con successo.");
    }
}