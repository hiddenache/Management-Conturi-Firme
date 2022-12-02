package screens;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;

public class ScreenListViewConturi extends Screen {
    private static final int STAGE_DEFAULT_WIDTH = 750;
    private static final int STAGE_DEFAULT_HEIGHT = 250;
    ListView<String> listView = new ListView<>();
    List<String> listaTranzactii;


    ObservableList<String> items;
    public ScreenListViewConturi(Connection sqlConnection, List<String> listaTranzactii) {
        this.sqlConnection=sqlConnection;
        this.listaTranzactii=listaTranzactii;
        items=  FXCollections.observableArrayList(listaTranzactii);
        createVBox();
        createScene();
        createStage();
        createControls();
        configureList();
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
        stage.show();
    }

    protected void createStage() {
        super.createStage(STAGE_DEFAULT_WIDTH, STAGE_DEFAULT_HEIGHT);
        this.stage.setTitle("Lista tranzactii");
    }

    protected void createVBox() {
        super.createVBox();
        vBox.getChildren().add(listView);
    }

    protected void createControls() {

    }

    private void configureList() {
        listView.setItems(items);
        Label label = new Label();
        label.setTextFill(Color.RED);
    }


}
