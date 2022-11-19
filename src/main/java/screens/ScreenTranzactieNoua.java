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
                alert.setTitle("ERROR");
                alert.setHeaderText("");
                message ="Check your data and try again!";
                alert.setContentText(message);
                alert.show();
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
            if(lblContCreditor.getText() == "" || lblContDebitor.getText().isBlank() || lblSuma.getText().isBlank() || lblDescriere.getText().isBlank()){
                System.out.println("CHECK YOUR DATA AND TRY AGAIN!");
            }
        });

    }
}
