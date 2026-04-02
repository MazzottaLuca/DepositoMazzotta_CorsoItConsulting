package fabbrica.personale;

import fabbrica.produzione.Macchina;

public class OperaioSpecial extends Operaio {

    public OperaioSpecial(String nome) {
        super(nome);
    }

    @Override
    public void lavora(Macchina m) {
        System.out.println(nome + " (OperaioSpecial) accende la macchina con procedura speciale.");
        m.accendi();
    }

    @Override
    public void ferma(Macchina m) {
        System.out.println(nome + " (OperaioSpecial) spegne la macchina con procedura speciale.");
        m.spegni();
    }
}