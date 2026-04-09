public class Main {
    public static void main(String[] args) {

        // Hamburger base
        Hamburger hamburger = new BaseBurger();

        // Aggiungo formaggio e bacon (decoratori concatenati)
        hamburger = new FormaggioDecorator(hamburger);
        hamburger = new BaconDecorator(hamburger);

        // Output finale
        System.out.println("Ordine: " + hamburger.getDescrizione());
        System.out.println("Prezzo finale: " + hamburger.getPrezzo() + "euro");
    }
}