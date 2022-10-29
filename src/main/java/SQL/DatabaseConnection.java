package SQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    /**
     *  Exemplu de conectare
     *  DatabaseConnection connectNow = new DatabaseConnection();
     *  Connection connectDB = connectNow.getConnection();
     *
     *  String connectQuery = "SELECT * from cont";
     *
     *  try {
     *      Statement stmt = connectDB.createStatement();
     *
     *      ResultSet queryOutput = stmt.executeQuery(connectQuery);
     *
     *      while(queryOutput.next()){
     *          cevaLabel.setText(queryOutput.getString());
     *      }
     *  }
     */
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "Firma";
        String databaseUser = "";
        String databasePass = "";

        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            databaseLink = DriverManager.getConnection(url, databaseUser, databasePass);

        } catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
