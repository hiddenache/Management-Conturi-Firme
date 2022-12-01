package screens;


import com.example.Otherss.Suma;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ScreenBilant extends Screen {
    private static final int STAGE_DEFAULT_WIDTH = 600;
    private static final int STAGE_DEFAULT_HEIGHT = 300;

    public ScreenBilant() {

        createVBox();
        createScene();
        createStage();
        createControls();
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
        this.stage.show();
    }


    protected void createStage() {
        super.createStage(STAGE_DEFAULT_WIDTH, STAGE_DEFAULT_HEIGHT);
        this.stage.setTitle("");


    }

    protected void createControls() {
//   configureTable();
        TableView tableView = new TableView();

        TableColumn<Suma,String> columnCont=new TableColumn<>("Numar cont");
        columnCont.setCellValueFactory(new PropertyValueFactory<>("numarCont"));

        TableColumn<Suma,Float> columnBilant=new TableColumn<>("Bilant initial");
        columnBilant.setCellValueFactory(new PropertyValueFactory<>("bilantInitial"));

        TableColumn<Suma,Float> columnSumaCreditoare=new TableColumn<>("Suma creditoare");
        columnSumaCreditoare.setCellValueFactory(new PropertyValueFactory<>("sumaCreditoare"));

        TableColumn<Suma,Float> columnSumaDebitoare=new TableColumn<>("Suma debitoare");
        columnSumaDebitoare.setCellValueFactory(new PropertyValueFactory<>("sumaDebitoare"));

        tableView.getColumns().add(columnCont);
        tableView.getColumns().add(columnBilant);
        tableView.getColumns().add(columnSumaCreditoare);
        tableView.getColumns().add(columnSumaDebitoare);
        tableView.getItems().add(new Suma("Cont 1", 25,250,23132));
        vBox.getChildren().add(tableView);


    }


}
