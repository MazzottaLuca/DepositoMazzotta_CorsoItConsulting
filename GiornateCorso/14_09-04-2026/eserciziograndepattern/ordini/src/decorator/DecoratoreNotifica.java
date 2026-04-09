package decorator;

import strategy.DatiOrdine;
import strategy.StrategiaEvasione;

public class DecoratoreNotifica extends DecoratoreStrategia {

    public DecoratoreNotifica(StrategiaEvasione strategia) {
        super(strategia);
    }

    @Override
    public void eseguiEvasione(DatiOrdine ordine) {
        super.eseguiEvasione(ordine);
        System.out.println("[NOTIFICA] Email inviata al cliente " + ordine.getCliente()
                + " per l'ordine " + ordine.getId());
    }
}