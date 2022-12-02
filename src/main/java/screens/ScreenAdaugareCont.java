package screens;


import database.DatabaseOperations;
import javafx.collections.FXCollections;
import javafx.scene.control.*;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScreenAdaugareCont extends Screen {
    private static final int STAGE_DEFAULT_WIDTH = 600;
    private static final int STAGE_DEFAULT_HEIGHT = 600;
    private static TextArea txtNumarCont;

    private static ChoiceBox choiceBox;
    private static TextArea txtDescriere;
    private static TextArea txtSoldInitial;

    String[] items = { "ca", "pa", "ac" };

    public ScreenAdaugareCont(Connection sqlConnection) {
      this.sqlConnection=sqlConnection;
        createVBox();
        createScene();
        createStage();
        createControls();
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
        stage.show();
    }



    protected void createStage() {
        super.createStage(STAGE_DEFAULT_WIDTH, STAGE_DEFAULT_HEIGHT);
        this.stage.setTitle("Adaugare Cont");
    }

    protected void createControls() {
        txtNumarCont = new TextArea("");
        txtNumarCont.setPrefSize(scene.getWidth(), 100);
        txtDescriere = new TextArea("");
        txtSoldInitial = new TextArea("");
        Button btnAdaugare = new Button("Adaugare cont");
        choiceBox = new ChoiceBox(FXCollections.observableArrayList(items));
        Label lblTipCont = new Label("Tip cont - (ca)pital | (pa)siv | (ac)tiv");
        Label lblNume = new Label("Numar cont");
        Label lblDescriere = new Label("Descriere");
        Label lblSold = new Label("Sold initial");
        vBox.getChildren().addAll(lblNume, txtNumarCont, lblDescriere, txtDescriere, lblTipCont, choiceBox, lblSold, txtSoldInitial, btnAdaugare);

        Pattern pattern = Pattern.compile("[0-9+]");
        btnAdaugare.setOnMouseClicked(mouseEvent -> {
            DatabaseOperations op = new DatabaseOperations(sqlConnection);
            //   sqlConnection=getConnection().orElse(null);
            Matcher matcher = pattern.matcher(txtSoldInitial.getText()); // doar cifre
            if(txtNumarCont.getText().isEmpty()
                    || txtDescriere.getText().isEmpty()
                    || choiceBox.getSelectionModel().getSelectedItem() == null
                    || txtSoldInitial.getText().isEmpty()
                    || !matcher.find()
                    ||Float.parseFloat(txtSoldInitial.getText()) < 0)
                alertt.createInformationAlert("ERROR");
             else{
                    if(op.addAccount(txtNumarCont.getText(), txtDescriere.getText(),
                            choiceBox.getSelectionModel().getSelectedItem().toString(), Float.parseFloat(txtSoldInitial.getText()))==1) {
                       alertt.createInformationAlert("SUCCES");
                        stage.hide();
                    }
                    else alertt.createInformationAlert("ERROR");
                }

        });
    }

}
