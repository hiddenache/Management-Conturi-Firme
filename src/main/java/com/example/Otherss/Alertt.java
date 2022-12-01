package com.example.Otherss;

public class Alertt {
    public Alertt() {
    }
    public void createInformationAlert(String tip_alerta) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        String message;
        switch (tip_alerta) {
            case "TRANZACTIE":
                alert.setTitle("TRANSACTION");
                alert.setHeaderText("");
                message = "Your transaction has been sent successfully!";
                alert.setContentText(message);
                alert.show();
                break;
            case "SUCCES":
                alert.setTitle("SUCCES");
                alert.setHeaderText("");
                message = "Operation successfully completed";
                alert.setContentText(message);
                alert.show();
                break;
            case "EMPTY":
                javafx.scene.control.Alert empty = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                empty.setTitle("ERROR");
                empty.setHeaderText("");
                message = "Fill out all the text boxes!";
                empty.setContentText(message);
                empty.show();
                break;
            case "ERROR":
                javafx.scene.control.Alert error = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                error.setTitle("ERROR");
                error.setHeaderText("");
                message = "An error has been ocurred!";
                error.setContentText(message);
                error.show();
                break;
            case "NOACC":
                javafx.scene.control.Alert noAcc = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                noAcc.setTitle("ERROR");
                noAcc.setHeaderText("");
                message = "No account found!";
                noAcc.setContentText(message);
                noAcc.show();
        }
    }

}
