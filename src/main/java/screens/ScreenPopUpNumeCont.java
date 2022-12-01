package screens;

import database.DatabaseOperations;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScreenPopUpNumeCont extends Screen{
    private static final int STAGE_DEFAULT_WIDTH = 300;
    private static final int STAGE_DEFAULT_HEIGHT = 150;
    private static TextArea txtNumeCont;
    private List<String> listaTranzactii=new ArrayList<>();

    Button btnSearch;
    public ScreenPopUpNumeCont() {
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

        Label lblNume = new Label("Introduceti numarul contului:");
        txtNumeCont = new TextArea("");
        btnSearch=new Button("Cautare");

        vBox.getChildren().addAll(lblNume,txtNumeCont,btnSearch);
        createBtnHandlers();
    }

    private void createBtnHandlers() {
        btnSearch.setOnMouseClicked(mouseEvent -> {
            Set transactions = new HashSet();
            if(!txtNumeCont.getText().isBlank() || Integer.parseInt(txtNumeCont.getText()) < 0 || Integer.parseInt(txtNumeCont.getText()) > 99999){
                try {
                    DatabaseOperations op = new DatabaseOperations();
                    for(Object tran : op.getTransactions(sqlConnection, Integer.valueOf(txtNumeCont.getText()))){
                        transactions.add(tran);
                    }
                    listaTranzactii = transactions.stream().toList();
                    if(!listaTranzactii.isEmpty()){
                         new ScreenListViewConturi(listaTranzactii);
                    }
                    listaTranzactii.clear();
                    transactions.clear();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}

