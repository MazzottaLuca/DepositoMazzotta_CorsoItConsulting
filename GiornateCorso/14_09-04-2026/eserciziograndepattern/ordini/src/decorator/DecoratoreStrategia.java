package decorator;

import strategy.DatiOrdine;
import strategy.StrategiaEvasione;

public abstract class DecoratoreStrategia implements StrategiaEvasione {
    protected StrategiaEvasione strategiaDecorata;

    public DecoratoreStrategia(StrategiaEvasione strategia) {
        this.strategiaDecorata = strategia;
    }

    @Override
    public void eseguiEvasione(DatiOrdine ordine) {
        strategiaDecorata.eseguiEvasione(ordine);
    }

}
