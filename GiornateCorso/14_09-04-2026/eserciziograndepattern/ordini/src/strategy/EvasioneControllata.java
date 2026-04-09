package strategy;

public class EvasioneControllata implements StrategiaEvasione {
    @Override
    public void eseguiEvasione(DatiOrdine ordine) {
        double prezzoFinale = ordine.getPrezzo() * 0.95;
        System.out.println("Ordine " + ordine.getId() + " evaso con evasione controllata. "
                + "Prezzo finale: " + prezzoFinale);
    }
}
