public class Magazzino implements Observer { //classe magazzino con metodo nella classe observer implementato
    @Override
    public void update(Ordine ordine) {
        if (ordine.getStato().equals("IN PREPARAZIONE")) {
            System.out.println("Magazzino: Ordine " + ordine.getId() + " in preparazione");
        }
    }
}
