import java.sql.*;



public class DatabaseConnection3 {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/sakila"; //in questo caso server locale
        String username = "root";
        String psw = "";
        String query = "SELECT * FROM actor LIMIT 5"; //scrivi query
        int nRow=5;
        try(Connection conn = DriverManager.getConnection(url, username, psw);
        PreparedStatement pstmt =conn.prepareStatement(query);) {
            pstmt.setInt(1, nRow);
            ResultSet result = pstmt.executeQuery(query); //esegui query 
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