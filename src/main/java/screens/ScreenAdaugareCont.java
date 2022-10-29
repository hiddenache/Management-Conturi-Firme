package screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ScreenAdaugareCont extends Screen {
    private static final int  STAGE_DEFAULT_WIDTH=600;
    private static final int STAGE_DEFAULT_HEIGHT=600;
    private static TextArea txtNumeCont;
    private static TextArea txtDescriere;
    private static TextArea txtSoldInitial;
    private static Button btnAdaugare;

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
        Label lblNume=new Label("Nume cont");
        Label lblDescriere=new Label("Descriere");
        Label lblSold=new Label("Sold initial");
        vBox.getChildren().addAll(lblNume,txtNumeCont,lblDescriere,txtDescriere,lblSold,txtSoldInitial,btnAdaugare);
    }

}
