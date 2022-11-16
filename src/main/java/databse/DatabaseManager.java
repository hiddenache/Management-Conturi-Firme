package databse;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

public class DatabaseManager {
    private static final String dbName = "firma";
    private static final String user= "root";
    private static final String password = "";
     private static final String url = "jdbc:mysql://localhost/" + dbName;

    /**
     *  DOWNLOAD XAMPP
     *  START Apache and Mysql from XAMPP
     *  Click on Admin button from Mysql tab
     *  Click on IMPORT Button
     *  Select your database to be imported
     */
    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());


    public Optional<Connection> connect() {

            try {

                Connection connection = DriverManager.getConnection(url, user, password);
                LOGGER.info("Database connected");
                return Optional.of(connection);
            } catch (SQLException e) {
                LOGGER.severe("Nu s-a putut realiza conexiunea la baza de date");
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Eroare conexiune bvaza de date");
//                alert.setHeaderText("");
//                String message ="Nu s-a putut realiza conexiunea la baza de date";
//                alert.setContentText(message);
//                alert.show();

        }
        return Optional.empty();
    }


}
