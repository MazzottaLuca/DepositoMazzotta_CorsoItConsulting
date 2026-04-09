public class BaconDecorator extends HamburgerDecorator {

    public BaconDecorator(Hamburger hamburger) {
        super(hamburger);
    }

    @Override
    public String getDescrizione() {
        return hamburger.getDescrizione() + ", bacon";
    }

    @Override
    public double getPrezzo() {
        return hamburger.getPrezzo() + 0.80; //aumento di prezzo con il bacon
    }
}