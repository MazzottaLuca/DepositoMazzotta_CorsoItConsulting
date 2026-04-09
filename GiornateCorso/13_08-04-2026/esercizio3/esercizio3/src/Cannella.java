public class Cannella extends IngredienteDecorator {
    public Cannella(Bevanda bevanda){
        super(bevanda);
    }
    @Override
    public String getDescrizione(){
        return bevanda.getDescrizione() + ", Cannella";
    }
    @Override
    public double getCosto(){
        return bevanda.getCosto() + 0.20;
    }
}
