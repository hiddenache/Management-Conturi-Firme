package screens;

import javafx.scene.control.Button;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ScreenAfisareTranzactii extends Screen {
    private static final int STAGE_DEFAULT_WIDTH = 600;
    private static final int STAGE_DEFAULT_HEIGHT = 200;
    private static Button btnTranzactiiNumeCont, btnTranzactiiData, btnTranzactiiTipCont;
    private List<String> listaTranzactii = new ArrayList<>();


    public ScreenAfisareTranzactii(Connection sqlConnection) {
        this.sqlConnection=sqlConnection;

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
        this.stage.setTitle("Meniu afisare tranzactii");


    }

    protected void createControls() {
        btnTranzactiiNumeCont = new Button("Afisare tranzactii pentru un  cont");
        btnTranzactiiData = new Button("Afisarea tranzactiilor dintr-o anumita perioada");
        btnTranzactiiTipCont = new Button("Afisarea tranzactiilor care implica conturi de un anumit tip");
        vBox.getChildren().addAll(btnTranzactiiNumeCont, btnTranzactiiData, btnTranzactiiTipCont);
        createBtnHandlers();

    }

    private void createBtnHandlers() {
        btnTranzactiiTipCont.setOnMouseClicked(mouseEvent -> new ScreenAfisareTranzactiiTipCont(sqlConnection));
        btnTranzactiiData.setOnMouseClicked(mouseEvent -> {
          new ScreenPopUpData(sqlConnection);


        });
        btnTranzactiiNumeCont.setOnMouseClicked(mouseEvent -> {
            new ScreenPopUpNumeCont(sqlConnection);
        });

    }

    private void createAlert() {

    }


}
