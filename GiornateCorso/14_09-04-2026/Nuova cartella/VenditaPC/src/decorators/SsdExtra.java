package decorators;

import model.Computer;
/**
 * CONCRETE DECORATOR
 * Aggiunge funzionalità (descrizione e prezzo) all'oggetto originale.
 */
public class SsdExtra extends ComponenteExtraDecorator {
    public SsdExtra(Computer computer) {
        super(computer);
    }

    @Override
    public String getDescrizione() {
        return computerRiferimento.getDescrizione() + " + SSD 2TB NVMe";
    }

    @Override
    public double getPrezzo() {
        return computerRiferimento.getPrezzo() + 150.00;
    }
}