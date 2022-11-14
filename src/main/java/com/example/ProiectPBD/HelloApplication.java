package com.example.ProiectPBD;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import screens.*;

public class HelloApplication extends Application {
    private final VBox root = new VBox(10);
    private static final int SCENE_DEFAULT_WIDTH = 350;
    private static final int SCENE_DEFAULT_HEIGHT = 250;
    private static final int BUTTON_WIDTH=250;
    private static final int BUTTOW_HEIGHT=25;
    private final Scene SCENE = new Scene(root, SCENE_DEFAULT_WIDTH, SCENE_DEFAULT_HEIGHT);
   private static  Button btnAdaugareCont,btnTranzactieNoua,btnSoldCurent,btnAfisareTranzactii,btnCalculareBilant,btnStergereCont;
    @Override
    public void start(Stage stage) {

        root.setAlignment(Pos.TOP_CENTER);
        root.prefHeightProperty().bind(SCENE.heightProperty());
        root.prefWidthProperty().bind(SCENE.widthProperty());
        createButtons();
        root.getChildren().addAll(btnAdaugareCont,btnTranzactieNoua,btnSoldCurent,btnAfisareTranzactii,btnCalculareBilant,btnStergereCont);
        stage.setScene(SCENE);
        stage.setTitle("Meniu Principal");
        createBtnHandlers();
        stage.show();

    }
    private void  createButtons() {
        btnAdaugareCont = new Button("Adaugare cont");
        btnTranzactieNoua = new Button("Tranzactie noua");
        btnSoldCurent = new Button("Sold curent");
        btnAfisareTranzactii = new Button("Afisare tranzactii");
        btnCalculareBilant = new Button("Calculare bilant initial");
        btnStergereCont = new Button("Stergere cont");
        /* ------------------------------------------------------ Preferred size ----------------------------------------------------- */
        btnAdaugareCont.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnTranzactieNoua.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnSoldCurent.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnAfisareTranzactii.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnCalculareBilant.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnStergereCont.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);

        /* ------------------------------------------------------ Handlers ----------------------------------------------------- */

    }
    private void createBtnHandlers() {
        btnAdaugareCont.setOnMouseClicked(mouseEvent -> new ScreenAdaugareCont());
        btnTranzactieNoua.setOnMouseClicked(mouseEvent -> new ScreenTranzactieNoua());
        btnStergereCont.setOnMouseClicked(mouseEvent -> new ScreenStergereCont());
        btnAfisareTranzactii.setOnMouseClicked(mouseEvent -> new ScreenAfisareTranzactii());
        btnSoldCurent.setOnMouseClicked(mouseEvent -> new ScreenSoldCurent());
    }






        public static void main (String[]args){

        launch();
        }
    }






