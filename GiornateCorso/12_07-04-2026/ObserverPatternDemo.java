import java.util.ArrayList;
import java.util.List;

public class ObserverPatternDemo {

    // Interfaccia Observer
    interface Observer {
        void update(String message);
    }

    // Interfaccia Subject
    interface Subject {
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObservers();
    }

    // ConcreteSubject
    static class ConcreteSubject implements Subject {
        private List<Observer> observers = new ArrayList<>();
        private String state;

        public void setState(String state) {
            this.state = state;
            notifyObservers();
        }

        public void registerObserver(Observer o) {
            observers.add(o);
        }

        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        public void notifyObservers() {
            for (Observer o : observers) {
                o.update(state);
            }
        }
    }

    // ConcreteObserver
    static class ConcreteObserver implements Observer {
        private String name;

        public ConcreteObserver(String name) {
            this.name = name;
        }

        public void update(String message) {
            System.out.println(name + " ha ricevuto aggiornamento: " + message);
        }
    }

    // Main
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        ConcreteObserver observer1 = new ConcreteObserver("Osservatore 1");
        ConcreteObserver observer2 = new ConcreteObserver("Osservatore 2");

        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        subject.setState("Stato iniziale");

        subject.removeObserver(observer1);

        subject.setState("Nuovo stato");
    }
}