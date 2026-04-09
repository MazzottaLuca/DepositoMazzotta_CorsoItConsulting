package decorator;

import strategy.DatiOrdine;
import strategy.StrategiaEvasione;

public class DecoratoreLog extends DecoratoreStrategia {
    public DecoratoreLog(StrategiaEvasione strategia) {
        super(strategia);
    }

    @Override
    public void eseguiEvasione(DatiOrdine ordine) {
        System.out.println("[LOG] Inizio evasione ordine " + ordine.getId()
                + " - Cliente: " + ordine.getCliente());
        super.eseguiEvasione(ordine);
        System.out.println("[LOG] Fine evasione ordine " + ordine.getId());
    }
}
