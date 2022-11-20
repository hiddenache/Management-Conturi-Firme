package screens;

import databse.DatabaseOperations;
import javafx.collections.FXCollections;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.nio.file.spi.FileSystemProvider;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class ScreenAfisareTranzactiiTipCont extends  Screen {
    private static final int  STAGE_DEFAULT_WIDTH=300;
    private static final int STAGE_DEFAULT_HEIGHT=200;
    private static Button btnAfisare;
    String[] items = { "ca", "pa", "ac" };
    List<String> listaTranzactii=new ArrayList<>();


        public ScreenAfisareTranzactiiTipCont() {
        createVBox();
        createScene();
        createStage();
        createControls();
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
        vBox.setSpacing(40);
        stage.show();
    }
    protected void createStage(){
        super.createStage(STAGE_DEFAULT_WIDTH,STAGE_DEFAULT_HEIGHT);
        this.stage.setTitle("Afisare tranzactii");

    }
    protected void createControls(){
        TextArea txtTipCont = new TextArea("");
        txtTipCont.setPrefSize(scene.getWidth(),stage.getHeight());
        Label lblCont=new Label("Selectati tipul contului");
        btnAfisare=new Button("Afisare tranzactii");
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(items));
        vBox.getChildren().addAll(lblCont,choiceBox,btnAfisare);

        btnAfisare.setOnMouseClicked(mouseEvent -> {
            DatabaseOperations op = new DatabaseOperations();
            Set transactions = new HashSet();
            Set finalTransactions = new HashSet();
            String choice = choiceBox.getSelectionModel().getSelectedItem().toString();
            listaTranzactii.clear();
            List<String> transactionsIDs = op.getSpecificTypeTransactions(sqlConnection, choice);
            try{
                Statement statement = sqlConnection.createStatement();
                //String list = op.getSpecificTypeTransactions(sqlConnection, choice).stream().toList().toString().replace("[", "").replace("]", "");

                List<Integer> myList = new ArrayList<Integer>(op.getSpecificTypeTransactions(sqlConnection, choice).stream().toList());
                StringBuilder builder = new StringBuilder();
                // length of the list
                int locSize = myList.size();

                Object[] args = new Object[locSize];
                for( int i = 0; i < locSize; i++ ) {
                    builder.append(" "+myList.get(i)+", " );
                    args[i]= myList.get(i);
                }

                System.out.println(myList);
                System.out.println(builder.toString().trim());


                ResultSet test = statement.executeQuery("SELECT * from tranzactie where cont_debitor in ('"+builder.toString().trim()+"') or cont_creditor in ('"+builder.toString().trim()+"')");
                while(test.next()){
                    listaTranzactii.add(test.getObject(1).toString());
                    System.out.println(test);
                }

                System.out.println(op.getSpecificTypeTransactions(sqlConnection, choice));
                System.out.println(listaTranzactii);
                listaTranzactii.clear();

            } catch (Exception e){
                e.printStackTrace();
            }




            //ystem.out.println(op.getTransactions(sqlConnection, 5));

            /*listaTranzactii = finalTransactions.stream().toList();
            if(!listaTranzactii.isEmpty()){
                ScreenListViewConturi viewConturi = new ScreenListViewConturi(listaTranzactii);
            }
            finalTransactions.clear();*/

        });
    }
}
