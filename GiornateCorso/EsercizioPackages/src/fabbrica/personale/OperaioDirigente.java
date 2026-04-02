package fabbrica.personale;

import fabbrica.produzione.Macchina;

public class OperaioDirigente extends Operaio {

    public OperaioDirigente(String nome) {
        super(nome);
    }

    @Override
    public void lavora(Macchina m) {
        System.out.println(nome + " (OperaioDirigente) accende la macchina in modalità dirigente.");
        m.accendi();
    }

    @Override
    public void ferma(Macchina m) {
        System.out.println(nome + " (OperaioDirigente) spegne la macchina in modalità dirigente.");
        m.spegni();
    }

    public void controllaMacchina(Macchina m) {
        System.out.print(nome + " controlla: ");
        m.stampaStato();
    }
}