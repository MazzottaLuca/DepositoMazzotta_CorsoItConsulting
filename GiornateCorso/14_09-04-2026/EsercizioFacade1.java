public class EsercizioFacade1 {
    static class LuceCamera {
        public void accendi() {
            System.out.println("Luce camera accesa");
        }
    }

    static class LuceCucina {
        public void accendi() {
            System.out.println("Luce cucina accesa");
        }
    }

    static class GestioneLuciFacade {
        private LuceCamera camera;
        private LuceCucina cucina;

        // costruttore
        public GestioneLuciFacade() {
            this.camera = new LuceCamera();
            this.cucina = new LuceCucina();
        }

        // metodo semplificato accendi tutte le luci
        public void accendiTutte() {
            System.out.println("accensione totale...");
            camera.accendi();
            cucina.accendi();
            System.out.println("Tutte le luci sono accese");
        }

    }

    public static void main(String[] args) {
        // istanza facade
        GestioneLuciFacade gestore = new GestioneLuciFacade();
        gestore.accendiTutte();
    }
}
