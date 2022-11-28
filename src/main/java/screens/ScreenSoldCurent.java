package screens;

import databse.DatabaseOperations;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ScreenSoldCurent extends Screen {

    private static final int STAGE_DEFAULT_WIDTH = 600;

    private static final int STAGE_DEFAULT_HEIGHT = 200;

    private static TextArea txtNrCont;

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

        Label nrCont = new Label("Numar cont");
        txtNrCont=new TextArea("");
        Button btnSold = new Button("Afisare sold");

        Label lblCont = new Label("Sold curent: ");
        Label sold = new Label();
        String columnLabel = "sold_initial";

        btnSold.setOnMouseClicked(mouseEvent -> {
            try {
                DatabaseOperations op = new DatabaseOperations();
                op.getInfo(sqlConnection,"SELECT " + columnLabel + " from cont where nr_cont='"+nrCont.getText()+"'", sold, columnLabel);
                //System.out.println(op.getInfo(sqlConnection,"SELECT " + columnLabel + " from cont where nr_cont='"+txtNrCont.getText()+"'", sold, columnLabel));
            }catch(Exception e){
                e.printStackTrace();
            }
        });

        vBox.getChildren().addAll(nrCont,txtNrCont, lblCont, sold, btnSold);
    }
}
