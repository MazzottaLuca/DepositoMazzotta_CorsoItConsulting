public class Panna extends IngredienteDecorator {
    public Panna(Bevanda bevanda){
        super(bevanda);
    }
    @Override
    public String getDescrizione(){
        return bevanda.getDescrizione() + ", Panna";
    }
    @Override
    public double getCosto(){
        return bevanda.getCosto() + 0.50;
    }
}
