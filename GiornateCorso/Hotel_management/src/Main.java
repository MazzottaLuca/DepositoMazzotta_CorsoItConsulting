import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Creazione DB e tabelle
        DBSetup.creaTabelle();

        boolean continua = true;
        while (continua) {
            System.out.println("===== MENU =====");
            System.out.println("1. Inserisci hotel e camere");
            System.out.println("2. Esci");
            System.out.print("Scelta: ");
            int scelta = sc.nextInt();
            sc.nextLine();

            switch (scelta) {
                case 1:
                    System.out.print("Nome hotel: ");
                    String nomeHotel = sc.nextLine();

                    try (Connection conn = DBConnection.getConnection()) {
                        // Inserimento hotel
                        PreparedStatement stmtHotel = conn.prepareStatement(
                                "INSERT INTO hotel (nome) VALUES (?)");
                        stmtHotel.setString(1, nomeHotel);
                        stmtHotel.executeUpdate();

                        // Inserimento camere standard
                        System.out.print("Quante camere standard vuoi inserire? ");
                        int numCamere = sc.nextInt();
                        sc.nextLine();

                        for (int i = 0; i < numCamere; i++) {
                            System.out.print("Numero camera: ");
                            int numero = sc.nextInt();
                            System.out.print("Prezzo camera: ");
                            float prezzo = sc.nextFloat();
                            sc.nextLine();

                            PreparedStatement stmtCamera = conn.prepareStatement(
                                    "INSERT INTO camera (numero, prezzo) VALUES (?, ?)");
                            stmtCamera.setInt(1, numero);
                            stmtCamera.setFloat(2, prezzo);
                            stmtCamera.executeUpdate();

                            // Collegamento hotel-camera
                            PreparedStatement stmtLink = conn.prepareStatement(
                                    "INSERT INTO hotel_camera (hotel_nome, camera_numero) VALUES (?, ?)");
                            stmtLink.setString(1, nomeHotel);
                            stmtLink.setInt(2, numero);
                            stmtLink.executeUpdate();
                        }

                        // Inserimento suite
                        System.out.print("Quante suite vuoi inserire? ");
                        int numSuite = sc.nextInt();
                        sc.nextLine();

                        for (int i = 0; i < numSuite; i++) {
                            System.out.print("Numero suite: ");
                            int numero = sc.nextInt();
                            System.out.print("Prezzo suite: ");
                            float prezzo = sc.nextFloat();
                            sc.nextLine();
                            System.out.print("Servizi extra: ");
                            String servizi = sc.nextLine();

                            // Inserimento camera base
                            PreparedStatement stmtCamera = conn.prepareStatement(
                                    "INSERT INTO camera (numero, prezzo) VALUES (?, ?)");
                            stmtCamera.setInt(1, numero);
                            stmtCamera.setFloat(2, prezzo);
                            stmtCamera.executeUpdate();

                            // Inserimento suite
                            PreparedStatement stmtSuite = conn.prepareStatement(
                                    "INSERT INTO suite (numero, serviziExtra) VALUES (?, ?)");
                            stmtSuite.setInt(1, numero);
                            stmtSuite.setString(2, servizi);
                            stmtSuite.executeUpdate();

                            // Collegamento hotel-camera
                            PreparedStatement stmtLink = conn.prepareStatement(
                                    "INSERT INTO hotel_camera (hotel_nome, camera_numero) VALUES (?, ?)");
                            stmtLink.setString(1, nomeHotel);
                            stmtLink.setInt(2, numero);
                            stmtLink.executeUpdate();
                        }

                        System.out.println("Hotel e camere inserite correttamente!");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    continua = false;
                    break;

                default:
                    System.out.println("Scelta non valida!");
            }
        }

        sc.close();
    }
}