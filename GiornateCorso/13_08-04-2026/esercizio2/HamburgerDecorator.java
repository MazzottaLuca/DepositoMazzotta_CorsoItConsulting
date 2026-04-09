public abstract class HamburgerDecorator implements Hamburger {

    protected Hamburger hamburger;

    public HamburgerDecorator(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    @Override
    public String getDescrizione() {
        return hamburger.getDescrizione();
    }

    @Override
    public double getPrezzo() {
        return hamburger.getPrezzo();
    }
}