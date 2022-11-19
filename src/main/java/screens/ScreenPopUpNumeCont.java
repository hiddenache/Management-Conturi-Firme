package screens;

import databse.DatabaseOperations;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class ScreenPopUpNumeCont extends Screen{
    private static final int STAGE_DEFAULT_WIDTH = 600;
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
        for(int i=0;i<15;i+=2)
        {
            listaTranzactii.add("Tranzactia "+i);
        }

        btnSearch.setOnMouseClicked(mouseEvent -> {
            try {
                DatabaseOperations op = new DatabaseOperations();
                listaTranzactii.add(op.getInfo2(sqlConnection, "SELECT * from tranzactie where cont_debitor='"+txtNumeCont.getText()+"'"));
                System.out.println(op.getInfo2(sqlConnection, "SELECT * from tranzactie where cont_debitor='"+txtNumeCont.getText()+"'"));
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println(listaTranzactii);
            ScreenListViewConturi viewConturi = new ScreenListViewConturi(listaTranzactii);
        });
    }

}

