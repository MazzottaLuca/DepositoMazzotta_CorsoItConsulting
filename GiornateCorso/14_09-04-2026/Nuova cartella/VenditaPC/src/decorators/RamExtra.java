package decorators;

import model.Computer;

public class RamExtra extends ComponenteExtraDecorator {
    public RamExtra(Computer computer) {
        super(computer);
    }

    @Override
    public String getDescrizione() {
        return computerRiferimento.getDescrizione() + " + Upgrade 32GB RAM DDR5";
    }

    @Override
    public double getPrezzo() {
        return computerRiferimento.getPrezzo() + 120.00;
    }
}