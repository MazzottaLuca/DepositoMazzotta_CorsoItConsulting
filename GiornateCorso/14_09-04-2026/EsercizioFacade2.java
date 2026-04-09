public class EsercizioFacade2 {
    // sottosistemi
    static class Bios{
        public void inizializza(){
            System.out.println("BIOS inizializzato");
        }
    }
    static class HardDisk{
        public void carica(){
            System.out.println("HD caricato");
        }
    }
    static class OS{
        public void avvia(){
            System.out.println("Sistema operativo avviato");
        }
    }
    //facade
    static class ComputerFacade{
        private Bios bios;
        private HardDisk hdd;
        private OS windows;
        public ComputerFacade() {
            this.bios = new Bios();
            this.hdd = new HardDisk();
            this.windows = new OS();
        }
        public void accendiComputer(){
            System.out.println("Avvio del sistema");
            bios.inizializza();
            hdd.carica();
            windows.avvia();
            System.out.println("Sistema pronto");
        }
    }
    //main
    public static void main(String[] args) {
        //istanza facade
        ComputerFacade pc = new ComputerFacade();
        //utilizziamo l'interfaccia sempllificata
        pc.accendiComputer();
    }
}
