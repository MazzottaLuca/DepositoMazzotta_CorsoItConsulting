package observer;

import java.util.ArrayList;
import java.util.List;

public class CentroProorita {
    private StatoCentro statoCorrente;
    private List<ObserverOrdine> observers = new ArrayList<>();

    public CentroProorita(StatoCentro statoIniziale) {
        this.statoCorrente = statoIniziale;
    }

    public void registraObserver(ObserverOrdine o) {
        observers.add(o);
    }

    public void rimuoviObserver(ObserverOrdine o) {
        observers.remove(o);
    }

    private void notificaObserver() {
        for (ObserverOrdine o : observers) {
            o.aggiorna(statoCorrente);
        }
    }

    public void setStato(StatoCentro nuovoStato) {
        System.out.println("\n[Centro] Cambio stato: " + statoCorrente + " -> " + nuovoStato);
        this.statoCorrente = nuovoStato;
        notificaObserver();
    }

    public StatoCentro getStato() {
        return statoCorrente;
    }
}