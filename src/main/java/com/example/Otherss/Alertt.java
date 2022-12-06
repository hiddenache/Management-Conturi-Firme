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
                break;
            case "DB_ERROR":
                javafx.scene.control.Alert dbEmpty = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                dbEmpty.setTitle("DATABASE ERROR");
                dbEmpty.setHeaderText("");
                message = "Couldn't connect to database";
                dbEmpty.setContentText(message);
                dbEmpty.show();
                break;
            case "DELETE_OK":
                alert.setTitle("");
                alert.setHeaderText("");
                message = "All inactive accounts have been deleted ";
                alert.setContentText(message);
                alert.show();
                break;
            case "DELETE_INFO":
                alert.setTitle("");
                alert.setHeaderText("");
                message = "There are no inactive accounts to delete";
                alert.setContentText(message);
                alert.show();
                break;
            case "NEGATIVE":
                alert.setTitle("");
                alert.setHeaderText("");
                message = "You cannot make a transaction the account has not enough money!";
                alert.setContentText(message);
                alert.show();
                break;
            case "NOTRANSACTIONS":
                alert.setTitle("");
                alert.setHeaderText("");
                message = "No transactions found!";
                alert.setContentText(message);
                alert.show();
                break;
            case "EXISTING":
                alert.setTitle("");
                alert.setHeaderText("");
                message = "An account with this name already exists! Try another name!";
                alert.setContentText(message);
                alert.show();
                break;
        }
    }

}
