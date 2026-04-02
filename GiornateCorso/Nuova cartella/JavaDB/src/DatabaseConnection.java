import java.sql.*;



public class DatabaseConnection {
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
            //System.out.println(result); //ottieni risultato

            /*//String query = "SELECT * FROM actors WHERE id =?"; //prima la query
            PreparedStatement pstmt= conn.prepareStatement(query); //fai il preparestatement passando la query
            pstmt.setInt(1, 1);*/
            //result = pstmt.execute();
            while(result.next()){
                System.out.println("Attore:"+ result.getString(2));//se gli dò la colonna nella query non la devo specificare ma mettere * (se li voglio tutti)
            }
            conn.close();
            //System.out.println("Connessione chiusa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}