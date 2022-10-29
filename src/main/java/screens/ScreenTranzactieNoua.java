package screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScreenTranzactieNoua extends Screen {
    private static final int  STAGE_DEFAULT_WIDTH=600;
    private static final int STAGE_DEFAULT_HEIGHT=600;

    private static TextArea txtContDebitor;
    private static TextArea txtContCreditor;
    private static TextArea txtDescriere;
    private static TextArea txtSuma;
    private static Button btnTransfer;


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
        txtContDebitor=new TextArea("");
        txtContDebitor.setPrefSize(scene.getWidth(),100);
        txtContCreditor=new TextArea("");
        txtContCreditor.setPrefSize(scene.getWidth(),100);
        txtSuma=new TextArea("");
        txtDescriere=new TextArea("");
        btnTransfer=new Button("Transfer");
        Label lblContCreditor=new Label("Cont creditor");
        Label lblContDebitor=new Label("Cont debitor");
        Label lblSuma=new Label("Suma de transferat");
        Label lblDescriere=new Label("Descriere tranzactie");
        vBox.getChildren().addAll(lblContDebitor,txtContDebitor,lblContCreditor,txtContCreditor,lblSuma,txtSuma,lblDescriere,txtDescriere,btnTransfer);

    }
}
