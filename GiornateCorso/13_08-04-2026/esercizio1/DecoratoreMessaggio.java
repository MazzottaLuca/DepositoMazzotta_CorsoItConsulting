public abstract class DecoratoreMessaggio implements Messaggio {

    protected Messaggio messaggio;

    public DecoratoreMessaggio(Messaggio messaggio) {
        this.messaggio = messaggio;
    }

    @Override
    public String getContenuto() {
        return messaggio.getContenuto();
    }
}