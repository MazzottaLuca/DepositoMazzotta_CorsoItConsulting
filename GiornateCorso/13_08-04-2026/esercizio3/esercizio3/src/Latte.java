public class Latte extends IngredienteDecorator {
    public Latte(Bevanda bevanda){
        super(bevanda);
    }
    @Override
    public String getDescrizione(){
        return bevanda.getDescrizione() + ", Latte";
    }
    @Override
    public double getCosto(){
        return bevanda.getCosto() + 0.30;
    }
}
