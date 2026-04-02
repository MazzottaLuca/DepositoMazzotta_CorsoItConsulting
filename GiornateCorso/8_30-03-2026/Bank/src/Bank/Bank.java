package Bank;

import java.sql.*;
import java.util.Scanner;

public class Bank {

    // Classe statica per l'account
    static class BankAccount {
        int id;
        String username; // nome utente
        double balance;

        public BankAccount(int id, String username, double balance) {
            this.id = id;
            this.username = username;
            this.balance = balance;
        }
    }

    // 🔹 Connessione DB
    public static Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/banca2";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Errore connessione DB");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = connect();
        if (conn == null) return;

        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU INIZIALE ---");
            System.out.println("1. Login");
            System.out.println("2. Registrazione");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            scanner.nextLine(); // pulire il buffer

            try {
                switch (scelta) {
                    case 1: // LOGIN
                        System.out.print("Username: ");
                        String userLogin = scanner.nextLine();
                        System.out.print("Password: ");
                        String passLogin = scanner.nextLine();

                        String sqlLogin = "SELECT * FROM accounts WHERE username=? AND password=?";
                        PreparedStatement psLogin = conn.prepareStatement(sqlLogin);
                        psLogin.setString(1, userLogin);
                        psLogin.setString(2, passLogin);
                        ResultSet rs = psLogin.executeQuery();

                        if (rs.next()) {
                            BankAccount currentAccount = new BankAccount(
                                    rs.getInt("id"),
                                    rs.getString("username"),
                                    rs.getDouble("balance")
                            );
                            System.out.println("Login riuscito!");
                            accountMenu(scanner, conn, currentAccount);
                        } else {
                            System.out.println("Credenziali errate.");
                        }
                        break;

                    case 2: // REGISTRAZIONE
                        System.out.print("Username: ");
                        String userReg = scanner.nextLine();
                        System.out.print("Password: ");
                        String passReg = scanner.nextLine();
                        System.out.print("Saldo iniziale: ");
                        double saldo = scanner.nextDouble();
                        scanner.nextLine(); // pulire buffer

                        String sqlReg = "INSERT INTO accounts (username, password, balance) VALUES (?, ?, ?)";
                        PreparedStatement psReg = conn.prepareStatement(sqlReg);
                        psReg.setString(1, userReg);
                        psReg.setString(2, passReg);
                        psReg.setDouble(3, saldo);
                        psReg.executeUpdate();

                        System.out.println("Registrazione completata! Torna al menu iniziale.");
                        break;

                    case 0: // USCITA
                        System.out.println("Uscita dal programma...");
                        running = false;
                        break;

                    default:
                        System.out.println("Scelta non valida.");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    // 🔹 MENU ACCOUNT SEPARATO
    public static void accountMenu(Scanner scanner, Connection conn, BankAccount account) throws SQLException {
        int choice;
        do {
            System.out.println("\n--- MENU ACCOUNT (" + account.username + ") ---");
            System.out.println("1. Deposito");
            System.out.println("2. Prelievo");
            System.out.println("3. Mostra saldo");
            System.out.println("0. Logout");
            System.out.print("Scelta: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: // Deposito
                    System.out.print("Importo: ");
                    double dep = scanner.nextDouble();
                    if (dep > 0) {
                        account.balance += dep;
                        PreparedStatement update = conn.prepareStatement(
                                "UPDATE accounts SET balance=? WHERE id=?"
                        );
                        update.setDouble(1, account.balance);
                        update.setInt(2, account.id);
                        update.executeUpdate();
                        System.out.println("Deposito effettuato!");
                    }
                    break;

                case 2: // Prelievo
                    System.out.print("Importo: ");
                    double pre = scanner.nextDouble();
                    if (pre > 0 && account.balance >= pre) {
                        account.balance -= pre;
                        PreparedStatement update = conn.prepareStatement(
                                "UPDATE accounts SET balance=? WHERE id=?"
                        );
                        update.setDouble(1, account.balance);
                        update.setInt(2, account.id);
                        update.executeUpdate();
                        System.out.println("Prelievo effettuato!");
                    } else {
                        System.out.println("Saldo insufficiente.");
                    }
                    break;

                case 3: // Mostra saldo
                    System.out.println("Saldo: " + account.balance);
                    break;

                case 0: // Logout
                    System.out.println("Logout...");
                    break;

                default:
                    System.out.println("Scelta non valida.");
                    break;
            }

        } while (choice != 0);
    }
}