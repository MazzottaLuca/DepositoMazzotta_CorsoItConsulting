package strategies;
/**
 * CONCRETE STRATEGY
 * Implementa una logica specifica di pagamento (Carta di Credito).
 */
public class PagamentoCarta implements StrategiaPagamento {
    @Override
    public void eseguiPagamento(double importo) {
        System.out.println("Transazione di " + importo + "euro autorizzata via Carta di Credito.");
    }
}