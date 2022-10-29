module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ProiectPBD to javafx.fxml;
    exports com.example.ProiectPBD;
}