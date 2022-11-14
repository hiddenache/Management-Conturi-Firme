package screens;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ScreenDetaliiTranzactie extends Screen {
    private static final int STAGE_DEFAULT_WIDTH = 600;
    private static final int STAGE_DEFAULT_HEIGHT = 600;
    private static final int TEXTAREA_HEIGHT=25;

    public ScreenDetaliiTranzactie(String title) {
        createVBox();
        createScene();
        createStage();
        createControls();
        this.stage.setTitle("Informatii " + title);
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
        stage.show();
    }

    protected void createStage() {
        super.createStage(STAGE_DEFAULT_WIDTH, STAGE_DEFAULT_HEIGHT);

    }

    protected void createControls() {
        TextArea txtTranzactie = new TextArea("");
        txtTranzactie.setPrefSize(STAGE_DEFAULT_WIDTH,TEXTAREA_HEIGHT);
        TextArea txtContDebitor = new TextArea("");
        txtContDebitor.setPrefSize(STAGE_DEFAULT_WIDTH,TEXTAREA_HEIGHT);
        TextArea txtContCreditor = new TextArea("");
        txtContCreditor.setPrefSize(STAGE_DEFAULT_WIDTH,TEXTAREA_HEIGHT);
        TextArea txtData = new TextArea("");
       txtData.setPrefSize(STAGE_DEFAULT_WIDTH,TEXTAREA_HEIGHT);
        TextArea txtSuma = new TextArea("");
        txtSuma.setPrefSize(STAGE_DEFAULT_WIDTH,TEXTAREA_HEIGHT);
        TextArea txtDecriere = new TextArea("");
        Label lblTranzactie = new Label("Numar tranzactie");
        Label lblContDebitor = new Label("Cont debitor");
        Label lblContCreditor = new Label("Cont creditor");
        Label lblData = new Label("Data tranzactie");
        Label lblSuma = new Label("Suma tranzactie");
        Label lbldescriere = new Label("Descriere tranzactie");
        vBox.getChildren().addAll(lblTranzactie,txtTranzactie,lblContDebitor,txtContDebitor,lblContCreditor,
                txtContCreditor,lblData,txtData,lblSuma,txtSuma,lbldescriere,txtDecriere);


    }
}
