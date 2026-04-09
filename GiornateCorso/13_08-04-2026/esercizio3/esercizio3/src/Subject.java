public interface Subject { //ha i metodi dell' observer astratto
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(String messaggio);
}