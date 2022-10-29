package screens;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ScreenSoldCurent extends Screen {

    private static final int STAGE_DEFAULT_WIDTH = 600;

    private static final int STAGE_DEFAULT_HEIGHT = 200;

    public ScreenSoldCurent() {
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
        this.stage.setTitle("Sold curent");
    }

    protected void createControls() {
        Label lblCont = new Label("Sold curent: ");
        Label sold = new Label("99.555$");
        vBox.getChildren().addAll(lblCont);
    }
}
