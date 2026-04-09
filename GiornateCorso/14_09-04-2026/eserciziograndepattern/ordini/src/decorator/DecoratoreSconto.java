package decorator;

import strategy.DatiOrdine;
import strategy.StrategiaEvasione;

public class DecoratoreSconto extends DecoratoreStrategia {
    private double percentualeSconto;

    public DecoratoreSconto(StrategiaEvasione strategia, double percentualeSconto) {
        super(strategia);
        this.percentualeSconto = percentualeSconto;
    }

    @Override
    public void eseguiEvasione(DatiOrdine ordine) {
        double prezzoOriginale = ordine.getPrezzo();
        double prezzoScontato = prezzoOriginale * (1 - percentualeSconto / 100);
        ordine.setPrezzo(prezzoScontato);
        System.out.println("[SCONTO] Applicato sconto del " + percentualeSconto
                + "% - Nuovo prezzo base: " + prezzoScontato);
        super.eseguiEvasione(ordine);
        ordine.setPrezzo(prezzoOriginale);
    }
}