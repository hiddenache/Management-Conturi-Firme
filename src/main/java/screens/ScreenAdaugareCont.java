package screens;


import databse.DatabaseOperations;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.Connection;

public class ScreenAdaugareCont extends Screen {
    private static final int  STAGE_DEFAULT_WIDTH=600;
    private static final int STAGE_DEFAULT_HEIGHT=600;
    private static TextArea txtNumeCont;

    private static ChoiceBox choiceBox;
    private static TextArea txtDescriere;
    private static TextArea txtSoldInitial;
    private static Button btnAdaugare;

    String items[] = {"ca", "pa", "ac"};

    public ScreenAdaugareCont() {
        createVBox();
         createScene();
         createStage();
         createControls();
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
         stage.show();
    }

    protected void createStage(){
     super.createStage(STAGE_DEFAULT_WIDTH,STAGE_DEFAULT_HEIGHT);
     this.stage.setTitle("Adaugare Cont");
    }

    protected void createControls(){
        txtNumeCont=new TextArea("");
        txtNumeCont.setPrefSize(scene.getWidth(),100);
        txtDescriere=new TextArea("");
        txtSoldInitial=new TextArea("");
        btnAdaugare=new Button("Adaugare cont");
        choiceBox = new ChoiceBox(FXCollections.observableArrayList(items));
        Label lblTipCont = new Label("Tip cont - (ca)pital | (pa)siv | (ac)tiv");
        Label lblNume=new Label("Nume cont");
        Label lblDescriere=new Label("Descriere");
        Label lblSold=new Label("Sold initial");
        vBox.getChildren().addAll(lblNume,txtNumeCont,lblDescriere,txtDescriere,lblTipCont, choiceBox,lblSold,txtSoldInitial,btnAdaugare);

        /**
         *  The conditions will be added soon
         *  txtNumeCont should not be NULL
         *  txtSoldInitail should not be NULL or invalid(ex. -1244$)
         *  etc...
         */
        btnAdaugare.setOnMouseClicked(mouseEvent -> {
            DatabaseOperations op = new DatabaseOperations();
         //   sqlConnection=getConnection().orElse(null);
            op.addAccount(sqlConnection,txtNumeCont.getText(), txtDescriere.getText(), choiceBox.getSelectionModel().getSelectedItem().toString(), Float.valueOf(txtSoldInitial.getText()));
        });
    }

}
