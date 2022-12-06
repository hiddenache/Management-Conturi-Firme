package screens;

import com.example.Otherss.Alertt;
import database.DatabaseOperations;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.Connection;

public class ScreenSoldCurent extends Screen {

    private static final int STAGE_DEFAULT_WIDTH = 600;

    private static final int STAGE_DEFAULT_HEIGHT = 200;

    private static TextArea txtNrCont;

    public ScreenSoldCurent(Connection sqlConnection) {
        this.sqlConnection = sqlConnection;

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
        txtNrCont = new TextArea("");
        Button btnSold = new Button("Afisare sold");

        Label lblCont = new Label("Sold curent: ");
        Label sold = new Label();
        String columnLabel = "sold_initial";

        btnSold.setOnMouseClicked(mouseEvent -> {
            try {
                DatabaseOperations op = new DatabaseOperations(sqlConnection);
                Alertt alert = new Alertt();
                if (txtNrCont.getText().isBlank() || txtNrCont.getText().isEmpty()) {
                    alert.createInformationAlert("EMPTY");
                } else {
                    if (op.checkIfAccExists(txtNrCont.getText())) {
                        sold.setText(op.getSoldCurent("SELECT " + columnLabel + " from cont where nr_cont='" + txtNrCont.getText() + "'", columnLabel) + "$");
                    } else {
                        alert.createInformationAlert("NOACC");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        vBox.getChildren().addAll(nrCont, txtNrCont, lblCont, sold, btnSold);
    }
}
