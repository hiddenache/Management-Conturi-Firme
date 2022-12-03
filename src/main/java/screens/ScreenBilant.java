package screens;


import com.example.Otherss.Suma;
import database.DatabaseOperations;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.ArrayList;


public class ScreenBilant extends Screen {
    private static final int STAGE_DEFAULT_WIDTH = 600;
    private static final int STAGE_DEFAULT_HEIGHT = 300;
    private ArrayList<Suma> listSuma = new ArrayList<>();

    public ScreenBilant(Connection sqlConnection) {
        this.sqlConnection = sqlConnection;
        createVBox();
        createScene();
        createStage();
        createControls();
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
        if (listSuma.size() > 0)
            this.stage.show();
        else alertt.createInformationAlert("NOACC");
    }


    protected void createStage() {
        super.createStage(STAGE_DEFAULT_WIDTH, STAGE_DEFAULT_HEIGHT);
        this.stage.setTitle("");


    }

    protected void createControls() {
//   configureTable();
        TableView tableView = new TableView();

        TableColumn<Suma, Integer> columnCont = new TableColumn<>("Numar cont");
        columnCont.setCellValueFactory(new PropertyValueFactory<>("numarCont"));

        TableColumn<Suma, Float> columnBilant = new TableColumn<>("Bilant initial");
        columnBilant.setCellValueFactory(new PropertyValueFactory<>("bilantInitial"));

        TableColumn<Suma, Float> columnSumaCreditoare = new TableColumn<>("Suma creditoare");
        columnSumaCreditoare.setCellValueFactory(new PropertyValueFactory<>("sumaCreditoare"));

        TableColumn<Suma, Float> columnSumaDebitoare = new TableColumn<>("Suma debitoare");
        columnSumaDebitoare.setCellValueFactory(new PropertyValueFactory<>("sumaDebitoare"));

        tableView.getColumns().add(columnCont);
        tableView.getColumns().add(columnBilant);
        tableView.getColumns().add(columnSumaCreditoare);
        tableView.getColumns().add(columnSumaDebitoare);
        DatabaseOperations databaseOperations = new DatabaseOperations(sqlConnection);
        listSuma = databaseOperations.getBilant();

        for (Suma suma : listSuma) {
            tableView.getItems().add(suma);
        }
        vBox.getChildren().add(tableView);


    }


}
