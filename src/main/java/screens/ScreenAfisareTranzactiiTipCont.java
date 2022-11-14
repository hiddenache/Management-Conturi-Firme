package screens;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class ScreenAfisareTranzactiiTipCont extends  Screen {
    private static final int  STAGE_DEFAULT_WIDTH=300;
    private static final int STAGE_DEFAULT_HEIGHT=200;
    private static Button btnAfisare;
    String[] items = { "ca", "pa", "ac" };
    List<String> listaTranzactii=new ArrayList<>();


        public ScreenAfisareTranzactiiTipCont() {
        createVBox();
        createScene();
        createStage();
        createControls();
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
        vBox.setSpacing(40);
        stage.show();
    }
    protected void createStage(){
        super.createStage(STAGE_DEFAULT_WIDTH,STAGE_DEFAULT_HEIGHT);
        this.stage.setTitle("Afisare tranzactii");

    }
    protected void createControls(){
        TextArea txtTipCont = new TextArea("");
        txtTipCont.setPrefSize(scene.getWidth(),stage.getHeight());
        Label lblCont=new Label("Selectati tipul contului");
        btnAfisare=new Button("Afisare tranzactii");
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(items));
        vBox.getChildren().addAll(lblCont,choiceBox,btnAfisare);
        createBtnHandlers();
    }
    private void createBtnHandlers() {
        btnAfisare.setOnMouseClicked(mouseEvent -> {
            //TODO
           for(int i=0;i<15;i+=2)
           {
               listaTranzactii.add("Tranzactia "+i);
           }
        new ScreenListViewConturi(listaTranzactii);

        });

    }
}
