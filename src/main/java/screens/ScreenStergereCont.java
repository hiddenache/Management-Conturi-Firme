package screens;

import database.DatabaseOperations;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.Connection;

public class ScreenStergereCont extends Screen {
    private static final int STAGE_DEFAULT_WIDTH = 600;
    private static final int STAGE_DEFAULT_HEIGHT = 200;

    public ScreenStergereCont(Connection sqlConnection) {
        this.sqlConnection=sqlConnection;

        createVBox();
        createScene();
        createStage();
        createControls();
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
        stage.show();

    }


    private static TextArea txtCont;

    protected void createStage() {
        super.createStage(STAGE_DEFAULT_WIDTH, STAGE_DEFAULT_HEIGHT);
        this.stage.setTitle("Stergere cont");
    }

    @Override
    protected void createControls() {
        txtCont = new TextArea("");
        txtCont.setPrefSize(scene.getWidth(), 100);
        Label lblCont = new Label("Numar cont");
        Button btnDelete = new Button("Stergere cont");
        vBox.getChildren().addAll(lblCont, txtCont, btnDelete);

        btnDelete.setOnMouseClicked(mouseEvent -> {
            DatabaseOperations op = new DatabaseOperations(sqlConnection);
            // sqlConnection= getConnection().orElse(null);
            if(op.deleteAccount( txtCont.getText())==1) {
                stage.hide();
                alertt.createInformationAlert("SUCCES");
            }
            else  alertt.createInformationAlert("ERROR");
        });
    }
}

