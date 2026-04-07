public class Spedizioni implements Observer { //classe spedizioni con metodo nella classe observer implementato
    @Override
    public void update(Ordine ordine) {
        if (ordine.getStato().equals("SPEDITO")) {
            System.out.println("Spedizioni: Ordine " + ordine.getId() + " pronto per spedizione");
        }
    }
}