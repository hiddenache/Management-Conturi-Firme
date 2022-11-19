package databse;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseOperations {

    private String result;

    /**
     * Functie pentru preluarea de informatii dintr-un anumit tabel
     *
     * @params
     * query - comanda care trebuie sa se execute
     * text - textul care trebuie modificat in momentul cand comanda returneaza o informatie
     * columnLabel - coloana din baza de date de care avem nevoie pentru a retrage informatii
     */
    public String getInfo(Connection dbConnection ,String query, Label text, String columnLabel){
        try{
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                text.setText(resultSet.getString(columnLabel));
            }
            resultSet.close();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public String getInfo2(Connection dbConnection ,String query){
        try{
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.close();
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int deleteAccount(Connection dbConnection,String accNum){

        String queryOp = "DELETE from cont WHERE nr_cont='"+accNum+"'";
        try{
            Statement statement = dbConnection.createStatement();
            int result = statement.executeUpdate(queryOp);
            if (result == 1)
                return 1;

        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    public int addAccount(Connection dbConnection,String numeCont, String descriere, String tipCont, float sold_initial){

        String queryOp = "INSERT into cont (nume_cont, descriere, tip_cont, sold_initial)" +
                "VALUES ('"+numeCont+"','"+descriere+"','"+tipCont+"','"+sold_initial+"')";

        try{
            Statement statement = dbConnection.createStatement();
            int result = statement.executeUpdate(queryOp);
            if (result == 1)
                return 1;
                //System.out.println("Contul adaugat cu succes!");

        }catch (Exception e){
            e.printStackTrace();
        }
            return 0;
    }

    // debitorul da, creditorul primeste

    public int newTransaction(Connection dbConnection, int cont_creditor, int cont_debitor, float suma, String descriere){
        java.util.Date javaDate = new java.util.Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());

        float soldFinalCreditor=0; // cel care primeste
        float soldFinalDebitor=0; // cel care trimite

        try{
            Statement statement = dbConnection.createStatement();
            //String soldContCreditor = statement.executeQuery("SELECT sold_curent from cont where nr_cont='" + cont_creditor + "'"); // cel care primeste
            ResultSet soldContCreditor = statement.executeQuery("SELECT sold_curent from cont where nr_cont='" + cont_creditor + "'"); // cel care primeste
            while(soldContCreditor.next()){
                soldFinalCreditor = Float.parseFloat(soldContCreditor.getString("sold_curent")) + suma;
            }
            ResultSet soldContDebitor = statement.executeQuery("SELECT sold_curent from cont where nr_cont='" + cont_debitor + "'"); // cel care trimite
            while(soldContDebitor.next()){
                soldFinalDebitor = Float.parseFloat(soldContDebitor.getString("sold_curent")) - suma;
            }

            String insertSoldFinalCreditor = "UPDATE cont SET sold_curent='"+soldFinalCreditor+"' where nr_cont='"+cont_creditor+"'";
            String insertSoldFinalDebitor = "UPDATE cont SET sold_curent='"+soldFinalDebitor+"' where nr_cont='"+cont_debitor+"'";

            statement.executeUpdate(insertSoldFinalCreditor);
            statement.executeUpdate(insertSoldFinalDebitor);

            String newTransactionInfo = "INSERT into tranzactie (cont_debitor, cont_creditor, data_tranzactie, suma_tranzactie, descriere_tranzactie)" +
                    "VALUES ('"+cont_debitor+"','"+cont_creditor+"','"+mySQLDate+"','"+suma+"','"+descriere+"')";

            statement.executeUpdate(newTransactionInfo);
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }
}
