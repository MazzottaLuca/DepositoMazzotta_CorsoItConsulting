public class DecoratoreMaiuscolo extends DecoratoreMessaggio {

    public DecoratoreMaiuscolo(Messaggio messaggio) {
        super(messaggio);
    }

    @Override
    public String getContenuto() {
        return messaggio.getContenuto().toUpperCase();
    }
}