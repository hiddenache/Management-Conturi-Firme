package screens;

import Storage.Transaction;
import com.example.Otherss.Alertt;
import database.DatabaseOperations;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScreenAfisareTranzactiiTipCont extends Screen {
    private static final int STAGE_DEFAULT_WIDTH = 300;
    private static final int STAGE_DEFAULT_HEIGHT = 200;
    private static Button btnAfisare;
    String[] items = {"ca", "pa", "ac"};
    Set listaTranzactii = new HashSet();


    public ScreenAfisareTranzactiiTipCont(Connection sqlConnection) {
        this.sqlConnection = sqlConnection;
        createVBox();
        createScene();
        createStage();
        createControls();
        vBox.prefHeightProperty().bind(stage.heightProperty());
        vBox.prefWidthProperty().bind(stage.widthProperty());
        vBox.setSpacing(40);
        stage.show();
    }

    protected void createStage() {
        super.createStage(STAGE_DEFAULT_WIDTH, STAGE_DEFAULT_HEIGHT);
        this.stage.setTitle("Afisare tranzactii");

    }

    protected void createControls() {
        TextArea txtTipCont = new TextArea("");
        txtTipCont.setPrefSize(scene.getWidth(), stage.getHeight());
        Label lblCont = new Label("Selectati tipul contului");
        btnAfisare = new Button("Afisare tranzactii");
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(items));
        vBox.getChildren().addAll(lblCont, choiceBox, btnAfisare);

        Alertt alert = new Alertt();

        btnAfisare.setOnMouseClicked(mouseEvent -> {
            DatabaseOperations op = new DatabaseOperations(sqlConnection);
            Object choiceItem = choiceBox.getSelectionModel().getSelectedItem();
            String choice;
            if (choiceItem == null) {
                alert.createInformationAlert("EMPTY");
            } else {
                choice = choiceItem.toString();
                try {
                    Statement statement = sqlConnection.createStatement();
                    List<Integer> myList = new ArrayList<Integer>(op.getSpecificTypeTransactions(choice));
                    // length of the list
                    int listSize = myList.size();
                    int i;

                    for (i = 0; i < listSize; i++) {
                        ResultSet getTransactions = statement.executeQuery("SELECT * from tranzactie where cont_debitor='" + myList.get(i) + "' or cont_creditor='" + myList.get(i) + "'");
                        while (getTransactions.next()) {
                            int cont_debitor = getTransactions.getInt("cont_debitor");
                            int cont_creditor = getTransactions.getInt("cont_creditor");
                            Date date = getTransactions.getDate("data_tranzactie");
                            float suma = getTransactions.getFloat("suma_tranzactie");
                            String descriere = getTransactions.getString("descriere_tranzactie");

                            Transaction tran = new Transaction(cont_debitor, cont_creditor, date, suma, descriere);
                            listaTranzactii.add(tran);
                        }
                    }

                    if (!listaTranzactii.isEmpty()) {
                        ScreenListViewConturi viewConturi = new ScreenListViewConturi(sqlConnection, listaTranzactii.stream().toList());
                        viewConturi.setTitle("Tranzactii cont de tip " + choice);
                    } else {
                        alert.createInformationAlert("NOTRANSACTIONS");
                    }
                    listaTranzactii.clear();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });
    }
}
