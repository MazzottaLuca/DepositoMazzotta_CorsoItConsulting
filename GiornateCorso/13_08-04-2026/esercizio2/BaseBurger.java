public class BaseBurger implements Hamburger {

    @Override
    public String getDescrizione() {
        return "Hamburger base";
    }

    @Override
    public double getPrezzo() {
        return 5.00; //prezzo hamburger base senza decorator
    }
}