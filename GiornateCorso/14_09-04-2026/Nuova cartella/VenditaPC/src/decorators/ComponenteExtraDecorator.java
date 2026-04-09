package decorators;

import model.Computer;
//DECORATOR ASTRATTO
public abstract class ComponenteExtraDecorator implements Computer {
    protected Computer computerRiferimento;

    public ComponenteExtraDecorator(Computer computer) {
        this.computerRiferimento = computer;
    }
}