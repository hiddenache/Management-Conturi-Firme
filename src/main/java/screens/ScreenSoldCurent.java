package screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ScreenSoldCurent extends Screen{
    
    private static final int  STAGE_DEFAULT_WIDTH=600;
    
    private static final int STAGE_DEFAULT_HEIGHT=200;
    
    private static TextArea txtTipCont;

    public void ScreenSoldCurent() {
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
        this.stage.setTitle("Afisare tranzactii");
    }
    protected void createControls(){
        txtTipCont=new TextArea("");
        txtTipCont.setPrefSize(scene.getWidth(),stage.getHeight());
        Label lblCont=new Label("Tip cont");
        Button btnAfisare = new Button("Afisare tranzactii");
        vBox.getChildren().addAll(lblCont,txtTipCont,btnAfisare);

    }
}
