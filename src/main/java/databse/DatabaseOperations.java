package databse;

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

        String queryOp = query;
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

    public void deleteAccount(Connection dbConnection,String accNum){

        String queryOp = "DELETE from cont WHERE nr_cont='"+accNum+"'";
        try{
            Statement statement = dbConnection.createStatement();
            int result = statement.executeUpdate(queryOp);
            if (result == 1){
                System.out.println("Contul cu numarul " + accNum + " a fost sters cu succes!");
            } else if(result == 0){
                System.out.println("Contul cu numarul " + accNum + " nu exista!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addAccount(Connection dbConnection,String numeCont, String descriere, String tipCont, float sold_initial){

        String queryOp = "INSERT into cont (nume_cont, descriere, tip_cont, sold_initial)" +
                "VALUES ('"+numeCont+"','"+descriere+"','"+tipCont+"','"+sold_initial+"')";

        try{
            Statement statement = dbConnection.createStatement();
            int result = statement.executeUpdate(queryOp);
            if (result == 1){
                System.out.println("Contul adaugat cu succes!");
            } else if(result == 0){
                System.out.println("Nu se poate adauga contul!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
