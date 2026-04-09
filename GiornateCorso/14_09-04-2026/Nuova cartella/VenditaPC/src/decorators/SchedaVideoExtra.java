package decorators;

import model.Computer;
/**
 * CONCRETE DECORATOR
 * Aggiunge funzionalità (descrizione e prezzo) all'oggetto originale.
 */
public class SchedaVideoExtra extends ComponenteExtraDecorator {
    public SchedaVideoExtra(Computer computer) {
        super(computer);
    }

    @Override
    public String getDescrizione() {
        return computerRiferimento.getDescrizione() + " + GPU NVIDIA RTX 4070";
    }

    @Override
    public double getPrezzo() {
        // Aggiungiamo 600€ come da menu
        return computerRiferimento.getPrezzo() + 600.00;
    }
}