public class SistemaNotifiche implements Observer { //classe sistema notifiche con metodo nella classe observer implementato
    @Override
    public void update(Ordine ordine) {
        System.out.println("Notifica cliente: Il tuo ordine " + ordine.getId() + " è " + ordine.getStato());
    }
}