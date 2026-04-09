import java.util.ArrayList;
import java.util.List;

public class GestoreOrdini implements Subject { // singleton, uno solo per tutti
    // singleton, unica istanza
    private static GestoreOrdini instance;
    // lista ordini e observers
    private List<Bevanda> ordini;
    private List<Observer> observers;

    private GestoreOrdini() {
        ordini = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static GestoreOrdini getInstance() {
        if (instance == null) {
            instance = new GestoreOrdini();
        }
        return instance;
    }

    // metodi
    public void aggiungiOrdine(Bevanda bevanda) {

        ordini.add(bevanda);

        
        OrdineDAO.salvaOrdine(bevanda);

        notifyObservers("Nuovo ordine: " + bevanda.getDescrizione());
    }

    public void mostraOrdini() {
        for (Bevanda b : ordini) {
            System.out.println(b.getDescrizione() + " - euro" + b.getCosto());
        }
    }

    // metodi observers
    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(String messaggio) {
        for (Observer o : observers) {
            o.update(messaggio);
        }
    }
}