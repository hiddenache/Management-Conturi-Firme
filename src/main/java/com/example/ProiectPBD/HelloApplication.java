package com.example.ProiectPBD;

import com.example.Otherss.Alertt;
import database.DatabaseManager;
import database.DatabaseOperations;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import screens.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

import static javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST;
//TODO
/*
  -----------------------
  verificari screen sold curent
  verificari screen tranzactie noua
  verificari screenuri afisare tranzactii
  verificari conturi cu cele mai multe tranzactii (afisare mesaj daca nu exista niciun cont | mai multe conturi au acelasi nr de tranzactii);

  -----------------------
*/

public class HelloApplication extends Application {
    private final VBox root = new VBox(10);
    private static final int SCENE_DEFAULT_WIDTH = 350;
    private static final int SCENE_DEFAULT_HEIGHT = 250;
    private static final int BUTTON_WIDTH=250;
    private static final int BUTTOW_HEIGHT=25;
    private final Scene SCENE = new Scene(root, SCENE_DEFAULT_WIDTH, SCENE_DEFAULT_HEIGHT);
   private static  Button btnAdaugareCont,btnTranzactieNoua,btnSoldCurent,btnAfisareTranzactii,btnCalculareBilant,btnStergereCont,btnBestAccount;
   private static Alertt alertt=new Alertt();
   private Connection sqlConnection;
    private static final Logger LOGGER=Logger.getLogger(HelloApplication.class.getName());

    @Override
    public void start(Stage stage) {

        sqlConnection=getConnection().orElse(null);
        root.setAlignment(Pos.TOP_CENTER);
        root.prefHeightProperty().bind(SCENE.heightProperty());
        root.prefWidthProperty().bind(SCENE.widthProperty());
        createButtons();
        root.getChildren().addAll(btnAdaugareCont,btnTranzactieNoua,btnSoldCurent,btnAfisareTranzactii,btnCalculareBilant,btnStergereCont,btnBestAccount);
        stage.setScene(SCENE);
        stage.setTitle("Meniu Principal");
        createBtnHandlers();
        if(sqlConnection!=null)stage.show();
        else alertt.createInformationAlert("DB_ERROR");
        stage.getScene().getWindow().addEventFilter(WINDOW_CLOSE_REQUEST, this::closeWindowEvent);

    }
    private void  createButtons() {
        btnAdaugareCont = new Button("Adaugare cont");
        btnTranzactieNoua = new Button("Tranzactie noua");
        btnSoldCurent = new Button("Sold curent");
        btnAfisareTranzactii = new Button("Afisare tranzactii");
        btnCalculareBilant = new Button("Bilant initial, suma creditoare/debitoare");
        btnStergereCont = new Button("Stergere cont");
        btnBestAccount=new Button("Contul cu cele mai multe tranzactii");

        /* ------------------------------------------------------ Preferred size ----------------------------------------------------- */
        btnAdaugareCont.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnTranzactieNoua.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnSoldCurent.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnAfisareTranzactii.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnCalculareBilant.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnStergereCont.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);
        btnBestAccount.setPrefSize(BUTTON_WIDTH, BUTTOW_HEIGHT);

        /* ------------------------------------------------------ Handlers ----------------------------------------------------- */

    }
    private List<String> listaTranzactii=new ArrayList<>();
    private void createBtnHandlers() {
        btnAdaugareCont.setOnMouseClicked(mouseEvent -> new ScreenAdaugareCont(sqlConnection));
        btnTranzactieNoua.setOnMouseClicked(mouseEvent -> new ScreenTranzactieNoua(sqlConnection));
        btnStergereCont.setOnMouseClicked(mouseEvent -> new ScreenStergereCont(sqlConnection));
        btnAfisareTranzactii.setOnMouseClicked(mouseEvent -> new ScreenAfisareTranzactii(sqlConnection));
        btnSoldCurent.setOnMouseClicked(mouseEvent -> new ScreenSoldCurent(sqlConnection));
        btnCalculareBilant.setOnMouseClicked(mouseEvent -> new ScreenBilant(sqlConnection));
        Set transactions = new HashSet();
        btnBestAccount.setOnMouseClicked(mouseEvent -> {
            listaTranzactii.clear();
            DatabaseOperations op = new DatabaseOperations(sqlConnection);
            System.out.println(op.getBestAccount());

            for(Object tran : op.getBestAccount()){
                transactions.add(tran);
            }

            listaTranzactii = transactions.stream().toList();

            if(!listaTranzactii.isEmpty()){
                ScreenListViewConturi viewConturi = new ScreenListViewConturi(sqlConnection,listaTranzactii);
            }
            transactions.clear();
        });
    }






        public static void main (String[]args){

        launch();
        }
    private static Optional<Connection> getConnection() {

        DatabaseManager databaseManager = new DatabaseManager();
        return databaseManager.connect();


    }
    private void closeWindowEvent(WindowEvent event) {
        try {
           if(sqlConnection!=null){
               sqlConnection.close();
               LOGGER.info("Database has been closed");
           }


        } catch (SQLException e) {
            LOGGER.severe("An error occured while trying to close the database");
        }
    }

    }






