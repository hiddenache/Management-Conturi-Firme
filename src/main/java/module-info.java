module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ProiectPBD to javafx.fxml;
    opens com.example.Otherss to javafx.base;
    exports com.example.ProiectPBD;


}