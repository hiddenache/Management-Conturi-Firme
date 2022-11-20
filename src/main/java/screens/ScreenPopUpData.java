package screens;

import databse.DatabaseOperations;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ScreenPopUpData extends Screen{
    private static final int STAGE_DEFAULT_WIDTH = 600;
    private static final int STAGE_DEFAULT_HEIGHT = 250;
    DatePicker firstDatePicker=new DatePicker();
    DatePicker lastDatePicker=new DatePicker();
    private List<String> listaTranzactii=new ArrayList<>();
    Button btnSearch;
    public ScreenPopUpData() {
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
        this.stage.setTitle("");
    }
    protected void createControls() {

        Label lblFirstDate = new Label("Introduceti data de inceput:");
        Label lblLastDate = new Label("Introduceti data de sfarsit:");
       firstDatePicker.setOnAction(e->{
           LocalDate firstDate=firstDatePicker.getValue();
           System.out.println(firstDate);
       });
       lastDatePicker.setOnAction(e ->{
           LocalDate lastDate=lastDatePicker.getValue();
           System.out.println(lastDate);
       });

        btnSearch=new Button("Cautare");
        vBox.getChildren().addAll(lblFirstDate,firstDatePicker,lblLastDate,lastDatePicker,btnSearch);
        createBtnHandlers();
    }
    private void createBtnHandlers() {

        btnSearch.setOnMouseClicked(mouseEvent -> {
            DatabaseOperations op = new DatabaseOperations();
            System.out.println(op.getTransactionsFromDateToDate(sqlConnection, "2022-11-17", "2022-11-19"));
            new ScreenListViewConturi(listaTranzactii);
        });
    }
}
