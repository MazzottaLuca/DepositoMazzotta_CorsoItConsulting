package strategy;

public class EvasioneNormale implements StrategiaEvasione {
    @Override
    public void eseguiEvasione(DatiOrdine ordine) {
        double prezzoFinale = ordine.getPrezzo() * 1.05;
        System.out.println("Ordine " + ordine.getId() + " evaso con evasione normale. "
                + "Prezzo finale: " + prezzoFinale);
    }
}
