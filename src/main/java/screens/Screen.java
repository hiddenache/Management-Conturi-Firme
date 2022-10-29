package screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class Screen {
    protected static final int STAGE_DEFAULT_HEIGHT = 600;
    protected static final int SPACING_VALUE=10;
    protected VBox vBox;
    protected Scene scene;
    protected Stage stage;
    protected void createScene(){
        scene=new Scene(vBox);
    }
    protected  void  createStage(int stageWidth,int stageHeight){
        stage=new Stage();
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
        stage.setScene(scene);
    }
    protected void createVBox(){
        vBox=new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(SPACING_VALUE);
    }
    protected abstract void createControls();
    }


