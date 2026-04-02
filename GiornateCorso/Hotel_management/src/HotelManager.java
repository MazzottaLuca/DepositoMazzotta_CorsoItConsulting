import java.util.ArrayList;

public class HotelManager {
    private ArrayList<Hotel> listaHotel;

    public HotelManager() { listaHotel = new ArrayList<>(); }

    public void aggiungiHotel(Hotel h) { listaHotel.add(h); }
    public ArrayList<Hotel> getListaHotel() { return listaHotel; }

    public void stampaDettagli(boolean conPrezzo) {
        for (Hotel h : listaHotel) {
            System.out.println("=== Hotel: " + h.getNome() + " ===");
            for (Camera c : h.getListaCamere()) {
                c.dettagli(conPrezzo);
                System.out.println("----------");
            }
            int numSuite = Hotel.contaSuite(h.getListaCamere());
            System.out.println("Numero di suite: " + numSuite + "\n");
        }
    }

    public void inserisciDB() {
        for (Hotel h : listaHotel) {
            h.inserisciDB();
        }
        System.out.println("Tutti gli hotel inseriti nel database!");
    }
}