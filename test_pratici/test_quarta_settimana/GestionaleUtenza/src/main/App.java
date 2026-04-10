package main;

import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import db.DBConnection;
import java.util.Scanner;
import model.User;
import strategy.*;

public class App {

    public static void main(String[] args) {

        DBConnection.getInstance(); // inizializza DB

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();

        User loggedUser = null;

        // =========================
        // MENU INIZIALE
        // =========================
        int startChoice;

        do {
            System.out.println("\n===== MENU INIZIALE =====");
            System.out.println("1. Registrazione");
            System.out.println("2. Login");
            System.out.println("3. Esci");
            System.out.print("Scelta: ");

            startChoice = sc.nextInt();
            sc.nextLine();

            switch (startChoice) {

                case 1:
                    // REGISTRAZIONE
                    System.out.println("\n===== REGISTRAZIONE =====");

                    System.out.print("Username: ");
                    String regUser = sc.nextLine();

                    System.out.print("Password: ");
                    String regPass = sc.nextLine();

                    System.out.print("Ruolo (ADMIN / PRO / NORMAL): ");
                    String regRole = sc.nextLine().toUpperCase();

                    userDAO.registerUser(regUser, regPass, regRole);
                    break;

                case 2:
                    // LOGIN
                    System.out.println("\n===== LOGIN =====");

                    System.out.print("Username: ");
                    String username = sc.nextLine();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    loggedUser = userDAO.login(username, password);

                    if (loggedUser == null) {
                        System.out.println("Credenziali errate!");
                    } else {
                        System.out.println("Login effettuato come: " + loggedUser.getRole());
                    }
                    break;

                case 3:
                    System.out.println("Uscita...");
                    sc.close();
                    return;

                default:
                    System.out.println("Scelta non valida");
            }

        } while (loggedUser == null);

        // =========================
        // STRATEGY RUOLI
        // =========================
        PermissionStrategy strategy;

        switch (loggedUser.getRole().toUpperCase()) {
            case "ADMIN":
                strategy = new AdminPermission();
                break;
            case "PRO":
                strategy = new ProPermission();
                break;
            default:
                strategy = new NormalPermission();
        }

        strategy.execute();

        // =========================
        // MENU PER RUOLO
        // =========================
        boolean running = true;
        int choice;

        while (running) {

            System.out.println("\n===== MENU =====");

            if (loggedUser.getRole().equalsIgnoreCase("ADMIN")) {

                System.out.println("1. Aggiungi prodotto");
                System.out.println("2. Visualizza prodotti");
                System.out.println("3. Esci");

                System.out.print("Scelta: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Nome prodotto: ");
                        String name = sc.nextLine();

                        System.out.print("Prezzo: ");
                        double price = sc.nextDouble();
                        sc.nextLine();

                        productDAO.addProduct(name, price);
                        System.out.println("Prodotto inserito!");
                        break;

                    case 2:
                        //visualizzazione prodotti
                        productDAO.getProducts().forEach(p ->
                            System.out.println(p.getId() + " - " + p.getName() + " - " + p.getPrice())
                        );
                        break;

                    case 3:
                        System.out.println("Uscita...");
                        running = false;
                        break;
                }

            } else if (loggedUser.getRole().equalsIgnoreCase("PRO")) {

                System.out.println("1. Visualizza prodotti");
                System.out.println("2. Ordina prodotto");
                System.out.println("3. Esci");

                System.out.print("Scelta: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        //visualizza prodotti, li prende dalla lista con foreach
                        productDAO.getProducts().forEach(p ->
                            System.out.println(p.getId() + " - " + p.getName() + " - " + p.getPrice())
                        );
                        break;

                    case 2:
                        //ordina prodotto scrivendo id e quantità
                        System.out.print("ID prodotto: ");
                        int productId = sc.nextInt();

                        System.out.print("Quantità: ");
                        int qty = sc.nextInt();
                        sc.nextLine();

                        orderDAO.addOrder(loggedUser.getId(), productId, qty);
                        break;

                    case 3:
                        System.out.println("Uscita...");
                        running = false;
                        break;
                }

            } else {

                System.out.println("1. Visualizza prodotti");
                System.out.println("2. Esci");

                System.out.print("Scelta: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        productDAO.getProducts().forEach(p ->
                            System.out.println(p.getId() + " - " + p.getName() + " - " + p.getPrice())
                        );
                        break;

                    case 2:
                        System.out.println("Uscita...");
                        running = false;
                        break;
                }
            }
        }

        sc.close();
    }
}