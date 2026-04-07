import java.util.ArrayList;
import java.util.List;

public class GestoreOrdini {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Ordine ordine) {
        for (Observer obs : observers) {
            obs.update(ordine);
        }
    }
}