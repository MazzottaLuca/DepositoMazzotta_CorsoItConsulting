public class ClienteObserver implements Observer { //questo è observer concreto, cioè il cliente che riceve le notifiche automatiche
        // Nome dell'osservatore
    private String nome;

    public ClienteObserver(String nome) {
        this.nome = nome;
    }
// Metodo chiamato automaticamente dal Subject (GestoreOrdini)
@Override
    public void update(String messaggio) {
        System.out.println("[" + nome + "] Notifica: " + messaggio);
    }
}