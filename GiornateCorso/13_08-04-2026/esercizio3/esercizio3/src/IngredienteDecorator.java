public abstract class IngredienteDecorator implements Bevanda{
    protected Bevanda bevanda;

    public IngredienteDecorator(Bevanda bevanda) {
        this.bevanda = bevanda;
    }
    @Override
    public abstract String getDescrizione();
}
