package fabbrica.personale;

import fabbrica.produzione.Macchina;

public class Operaio {
    protected String nome;

    public Operaio(String nome) {
        this.nome = nome;
    }

    public void lavora(Macchina m) {
        System.out.println(nome + " (Operaio) accende la macchina.");
        m.accendi();
    }

    public void ferma(Macchina m) {
        System.out.println(nome + " (Operaio) spegne la macchina.");
        m.spegni();
    }

    public String getNome() {
        return nome;
    }
}