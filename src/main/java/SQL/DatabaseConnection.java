package SQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    /**
     *  DOWNLOAD XAMPP
     *  START Apache and Mysql from XAMPP
     *  Click on Admin button from Mysql tab
     *  Click on IMPORT Button
     *  Select your database to be imported
     */
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "firma";
        String databaseUser = "root";
        String databasePass = "";

        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePass);

        } catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
