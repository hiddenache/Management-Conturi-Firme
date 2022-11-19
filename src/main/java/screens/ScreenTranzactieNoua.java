package screens;

import databse.DatabaseOperations;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ScreenTranzactieNoua extends Screen {
    private static final int  STAGE_DEFAULT_WIDTH=600;
    private static final int STAGE_DEFAULT_HEIGHT=600;


    public ScreenTranzactieNoua() {
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
        this.stage.setTitle("Tranzactie Noua");
    }

    private void createInformationAlert(String tip_alerta)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String message = null;
        switch (tip_alerta){
            case "TRANZACTIE":
                alert.setTitle("TRANSACTION");
                alert.setHeaderText("");
                message ="Your transaction is sent successfully!";
                alert.setContentText(message);
                alert.show();
                break;
            case "EMPTY":
                Alert empty = new Alert(Alert.AlertType.ERROR);
                empty.setTitle("ERROR");
                empty.setHeaderText("");
                message ="Fill out all the text boxes!";
                empty.setContentText(message);
                empty.show();
                break;
            case "ERROR":
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("ERROR");
                error.setHeaderText("");
                message ="An error has been ocurred!";
                error.setContentText(message);
                error.show();
                break;
        }
    }

    protected void createControls(){
        TextArea txtContDebitor = new TextArea("");
        txtContDebitor.setPrefSize(scene.getWidth(),100);
        TextArea txtContCreditor = new TextArea("");
        txtContCreditor.setPrefSize(scene.getWidth(),100);
        TextArea txtSuma = new TextArea("");
        TextArea txtDescriere = new TextArea("");
        Button btnTransfer = new Button("Transfer");
        Label lblContCreditor=new Label("Cont creditor");
        Label lblContDebitor=new Label("Cont debitor");
        Label lblSuma=new Label("Suma de transferat");
        Label lblDescriere=new Label("Descriere tranzactie");

        vBox.getChildren().addAll(lblContDebitor, txtContDebitor,lblContCreditor, txtContCreditor,lblSuma, txtSuma,lblDescriere, txtDescriere, btnTransfer);

        btnTransfer.setOnMouseClicked(mouseEvent -> {
            DatabaseOperations op = new DatabaseOperations();
            if(txtContDebitor.getText().isBlank() || txtContCreditor.getText().isBlank() || txtSuma.getText().isBlank() || txtDescriere.getText().isBlank()){
                createInformationAlert("EMPTY");
            }
            else if(!(txtContCreditor.getText().matches("[0-9]")) || !(txtContDebitor.getText().matches("[0-9]")) || Float.valueOf(txtSuma.getText())<0){
                createInformationAlert("ERROR");
            }
            else if(op.newTransaction(sqlConnection, Integer.valueOf(txtContCreditor.getText()), Integer.valueOf(txtContDebitor.getText()), Float.valueOf(txtSuma.getText()), txtDescriere.getText())==0){
                createInformationAlert("ERROR");
            }

            op.newTransaction(sqlConnection, Integer.valueOf(txtContCreditor.getText()), Integer.valueOf(txtContDebitor.getText()), Float.valueOf(txtSuma.getText()), txtDescriere.getText());
        });

    }
}
