import java.sql.*;



public class DatabaseConnection2 {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/sakila"; //in questo caso server locale
        String username = "root";
        String psw = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, psw); //passiamo variabili a connection
            System.out.println("Connessione riuscita");
            Statement stmt =conn.createStatement(); //crea statement
            String query = "SELECT * FROM actor LIMIT 5"; //scrivi query
            ResultSet result = stmt.executeQuery(query); //esegui query 
            ResultSetMetaData meta = result.getMetaData();
            int numColumns= meta.getColumnCount();

            while(result.next()){
                for (int i = 1; i < numColumns; i++) { //ciclo le colonne per ottenere i valori
                    String column = meta.getColumnName(i);
                    Object val = result.getObject(i);
                    System.out.print(column+ ":"+ val);
                    if (i<numColumns){
                        System.out.print("|");
                    }
                }
            }
            conn.close();
            //System.out.println("Connessione chiusa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}