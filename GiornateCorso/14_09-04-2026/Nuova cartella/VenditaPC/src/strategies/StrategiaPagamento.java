package strategies;
/**
 * INTERFACCIA STRATEGY
 * Definisce il metodo comune per tutte le varianti di pagamento.
 */
public interface StrategiaPagamento {
    void eseguiPagamento(double importo);
}