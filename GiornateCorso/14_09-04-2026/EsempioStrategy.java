// Interfaccia Strategy
interface AttaccoStrategy {
    void attacca();
}

// Strategie concrete
class AttaccoMelee implements AttaccoStrategy {
    @Override
    public void attacca() {
        System.out.println("Colpisce da vicino!");
    }
}

class AttaccoDistanza implements AttaccoStrategy {
    @Override
    public void attacca() {
        System.out.println("Spara da lontano!");
    }
}

// Classe che usa la Strategy
class Arma {
    private AttaccoStrategy strategia;

    // costruttore
    public Arma(AttaccoStrategy strategia) {
        this.strategia = strategia;
    }

    // cambia strategia a runtime
    public void setStrategia(AttaccoStrategy strategia) {
        this.strategia = strategia;
    }

    public void faiDanno() {
        strategia.attacca();
    }
}

// Classe main per test
public class EsempioStrategy {
    public static void main(String[] args) {

        // arma con attacco a distanza
        Arma arma = new Arma(new AttaccoDistanza());
        arma.faiDanno(); // Spara da lontano!

        // cambio strategia a runtime
        arma.setStrategia(new AttaccoMelee());
        arma.faiDanno(); // Colpisce da vicino!
    }
}