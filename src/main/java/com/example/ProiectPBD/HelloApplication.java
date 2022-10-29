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
    private static final int SCENE_DEFAULT_WIDTH = 300;
    private static final int SCENE_DEFAULT_HEIGHT = 250;
    private static final int BUTTON_WIDTH=180;
    private static final int BUTTOW_HEIGHT=25;
    private final Scene SCENE = new Scene(root, SCENE_DEFAULT_WIDTH, SCENE_DEFAULT_HEIGHT);
   private static  Button btnAdaugareCont,btnTranzactieNoua,btnSoldCurent,btnAfisareTranzactii,btnAfisareTranzactiiData,btnCalculareBilant,btnStergereCont;
    @Override
    public void start(Stage stage) {
        root.setAlignment(Pos.TOP_CENTER);
        root.prefHeightProperty().bind(SCENE.heightProperty());
        root.prefWidthProperty().bind(SCENE.widthProperty());
        createButtons();
        root.getChildren().addAll(btnAdaugareCont,btnTranzactieNoua,btnSoldCurent,btnAfisareTranzactii,btnAfisareTranzactiiData,btnCalculareBilant,btnStergereCont);
        stage.setScene(SCENE);
        stage.setTitle("Meniu Principal");
        stage.show();

    }
    private void  createButtons() {
        btnAdaugareCont = new Button("Adaugare cont");
        btnTranzactieNoua = new Button("Tranzactie noua");
        btnSoldCurent = new Button("Sold curent");
        btnAfisareTranzactii = new Button("Afisare tranzactii pentru un cont");
        btnAfisareTranzactiiData = new Button("Afisare tranzactii dupa data");
        btnCalculareBilant = new Button("Calculare bilant initial");
        btnStergereCont = new Button("Stergere cont");
        /* ------------------------------------------------------ Preferred size ----------------------------------------------------- */
        btnAdaugareCont.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnTranzactieNoua.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnSoldCurent.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnAfisareTranzactii.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnAfisareTranzactiiData.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnCalculareBilant.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnStergereCont.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        CreateBtnHandlers();
        /* ------------------------------------------------------ Handlers ----------------------------------------------------- */

    }
    private void CreateBtnHandlers() {
        btnAdaugareCont.setOnMouseClicked(mouseEvent -> {
            ScreenAdaugareCont screenAdaugareCont = new ScreenAdaugareCont();

        });
        btnTranzactieNoua.setOnMouseClicked(mouseEvent -> {
            ScreenTranzactieNoua screenTranzactieNoua = new ScreenTranzactieNoua();

        });
        btnStergereCont.setOnMouseClicked(mouseEvent -> {
            ScreenStergereCont screenStergereCont = new ScreenStergereCont();
        });
        btnAfisareTranzactii.setOnMouseClicked(mouseEvent ->{
            ScreenAfisareTranzactii screenAfisareTranzactii=new ScreenAfisareTranzactii();
        } );
        btnAfisareTranzactii.setOnMouseClicked(mouseEvent ->{
            ScreenAfisareTranzactii screenAfisareTranzactii=new ScreenAfisareTranzactii();
        } );
    }






        public static void main (String[]args){

        launch();
        }
    }






