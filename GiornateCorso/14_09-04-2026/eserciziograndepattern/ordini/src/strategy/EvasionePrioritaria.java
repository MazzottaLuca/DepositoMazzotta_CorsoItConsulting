package strategy;

public class EvasionePrioritaria implements StrategiaEvasione {
    @Override
    public void eseguiEvasione(DatiOrdine ordine) {
        double prezzoFinale = ordine.getPrezzo() * 1.15;
        System.out.println("Ordine " + ordine.getId() + " evaso con evasione prioritaria. "
                + "Prezzo finale: " + prezzoFinale);
    }
}
