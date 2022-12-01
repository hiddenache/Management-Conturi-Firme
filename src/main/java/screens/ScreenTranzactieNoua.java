package screens;

import database.DatabaseOperations;
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
            op.newTransaction(sqlConnection, Integer.parseInt(txtContCreditor.getText().trim()), Integer.parseInt(txtContDebitor.getText().trim()), Float.valueOf(txtSuma.getText().trim()), txtDescriere.getText().trim());
            stage.hide();
        });

    }
}
