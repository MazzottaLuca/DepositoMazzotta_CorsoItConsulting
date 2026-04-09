public class MessaggioBase implements Messaggio {

    private String contenuto;

    public MessaggioBase(String contenuto) {
        this.contenuto = contenuto;
    }

    @Override
    public String getContenuto() {
        return contenuto;
    }
}