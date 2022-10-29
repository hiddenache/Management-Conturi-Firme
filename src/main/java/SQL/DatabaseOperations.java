package SQL;

import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

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
    public String getInfo(String query, Label text, String columnLabel){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String queryOp = query;
        try{
            Statement stmt = connectDB.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                text.setText(rs.getString(columnLabel));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public void deleteAccount(String accNum){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String queryOp = "DELETE from cont WHERE nr_cont='"+accNum+"'";
        try{
            Statement stmt = connectDB.createStatement();
            int result = stmt.executeUpdate(queryOp);
            if (result == 1){
                System.out.println("Contul cu numarul " + accNum + " a fost sters cu succes!");
            } else if(result == 0){
                System.out.println("Contul cu numarul " + accNum + " nu exista!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addAccount(String numeCont, String descriere, String tipCont, float sold_initial){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String queryOp = "INSERT into cont (nume_cont, descriere, tip_cont, sold_initial)" +
                "VALUES ('"+numeCont+"','"+descriere+"','"+tipCont+"','"+sold_initial+"')";

        try{
            Statement stmt = connectDB.createStatement();
            int result = stmt.executeUpdate(queryOp);
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
