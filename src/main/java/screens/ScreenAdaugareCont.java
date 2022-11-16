package screens;


import databse.DatabaseOperations;
import javafx.collections.FXCollections;
import javafx.scene.control.*;

public class ScreenAdaugareCont extends Screen {
    private static final int STAGE_DEFAULT_WIDTH = 600;
    private static final int STAGE_DEFAULT_HEIGHT = 600;
    private static TextArea txtNumarCont;

    private static ChoiceBox choiceBox;
    private static TextArea txtDescriere;
    private static TextArea txtSoldInitial;

    String[] items = { "ca", "pa", "ac" };

    public ScreenAdaugareCont() {
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

        /*
         *  The conditions will be added soon
         *  txtNumeCont should not be NULL
         *  txtSoldInitail should not be NULL or invalid(ex. -1244$)
         *  etc...
         */
        btnAdaugare.setOnMouseClicked(mouseEvent -> {
            DatabaseOperations op = new DatabaseOperations();
            //   sqlConnection=getConnection().orElse(null);
            if(op.addAccount(sqlConnection, txtNumarCont.getText(), txtDescriere.getText(),
                    choiceBox.getSelectionModel().getSelectedItem().toString(), Float.parseFloat(txtSoldInitial.getText()))==1)
                createInformationAlert(txtNumarCont.getText());
            else createErrorAllert(txtNumarCont.getText());
        });
    }
    private void createInformationAlert(String accNum)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Adaugare cont ");
        alert.setHeaderText("");
        String message ="Contul cu numarul " + accNum + " a fost adaugat cu succes!";
        alert.setContentText(message);
        alert.show();
    }
    private void createErrorAllert(String accNum){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Eroare adaugare  cont "+ accNum);
        alert.setHeaderText("");
        String message ="Contul cu numarul " + accNum + "nu a putut fi adaugat";
        alert.setContentText(message);
        alert.show();

    }

}
