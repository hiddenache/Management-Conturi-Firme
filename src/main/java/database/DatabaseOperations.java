package database;

import Storage.Transaction;
import com.example.Otherss.Alertt;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

public class DatabaseOperations {

    private String result;

    public Connection sqlConnection;

    public DatabaseOperations(Connection sqlConnection) {
        this.sqlConnection=sqlConnection;
    }

    /**
     * Functie pentru preluarea de informatii dintr-un anumit tabel
     *
     * @params query - comanda care trebuie sa se execute
     * text - textul care trebuie modificat in momentul cand comanda returneaza o informatie
     * columnLabel - coloana din baza de date de care avem nevoie pentru a retrage informatii
     */
    public String getInfo( String query, Label text, String columnLabel) {
        try {
            Statement statement = sqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                text.setText(resultSet.getString(columnLabel));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



    public int deleteAccount( String accNum) {

        String queryOp = "DELETE from cont WHERE nr_cont='" + accNum + "'";
        try {
            Statement statement = sqlConnection.createStatement();
            int result = statement.executeUpdate(queryOp);
            if (result == 1)
                return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int addAccount( String numeCont, String descriere, String tipCont, float sold_initial) {

        String queryOp = "INSERT into cont (nume_cont, descriere, tip_cont, sold_initial, sold_curent)" +
                "VALUES ('" + numeCont + "','" + descriere + "','" + tipCont + "','" + sold_initial + "', '" + sold_initial + "')";

        try {
            Statement statement =sqlConnection.createStatement();
            int result = statement.executeUpdate(queryOp);
            if (result == 1)
                return 1;
            //System.out.println("Contul adaugat cu succes!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // debitorul da, creditorul primeste

    public void newTransaction( int cont_creditor, int cont_debitor, Float suma, String descriere) {
        java.util.Date javaDate = new java.util.Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());

        float soldFinalCreditor; // cel care primeste
        float soldFinalDebitor; // cel care trimite

        boolean error = false;
        try {
            Statement statement = sqlConnection.createStatement();

            ResultSet validate_cont = statement.executeQuery("SELECT * from cont");

            // daca nu este numar atunci eroare
            if (!Integer.valueOf(cont_creditor).toString().matches("[0-9]+")
                    || !Integer.valueOf(cont_debitor).toString().matches("[0-9]+")
                    || suma < 0 || descriere.isBlank()) {
                error = true;
            }

            if (Integer.valueOf(cont_creditor).toString().isBlank()
                    || Integer.valueOf(cont_debitor).toString().isBlank()
                    || Float.valueOf(suma).toString().isBlank()
                    || descriere.isBlank()) {
                error = true;
            }

            if (Float.valueOf(suma).isNaN()) error = true;

            if (error == false) {
                ResultSet soldContCreditor = statement.executeQuery("SELECT * from cont where nr_cont='" + cont_creditor + "'"); // cel care primeste
                soldContCreditor.next();
                soldFinalCreditor = soldContCreditor.getFloat("sold_curent") + suma;
                //System.out.println(soldContCreditor.getFloat("sold_curent"));

                ResultSet soldContDebitor = statement.executeQuery("SELECT * from cont where nr_cont='" + cont_debitor + "'"); // cel care trimite
                soldContDebitor.next();
                //System.out.println(soldContDebitor.getFloat("sold_curent"));
                soldFinalDebitor = soldContDebitor.getFloat("sold_curent") - suma;

                String insertSoldFinalCreditor = "UPDATE cont SET sold_curent='" + soldFinalCreditor + "' where nr_cont='" + cont_creditor + "'";
                String insertSoldFinalDebitor = "UPDATE cont SET sold_curent='" + soldFinalDebitor + "' where nr_cont='" + cont_debitor + "'";

                statement.executeUpdate(insertSoldFinalCreditor);
                statement.executeUpdate(insertSoldFinalDebitor);

                String newTransactionInfo = "INSERT into tranzactie (cont_debitor, cont_creditor, data_tranzactie, suma_tranzactie, descriere_tranzactie)" +
                        "VALUES ('" + cont_debitor + "','" + cont_creditor + "','" + mySQLDate + "','" + suma + "','" + descriere + "')";

                statement.executeUpdate(newTransactionInfo);
                error = false;
            }

            if (error) {
                Alertt alertt=new Alertt();
                alertt.createInformationAlert("ERROR");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set getTransactions( int nr_cont) {
        String query = "SELECT * from tranzactie where cont_debitor='" + nr_cont + "' or cont_creditor='" + nr_cont + "'";
        Set<Transaction> transactions = new HashSet<Transaction>();

        try {
            Statement statement = sqlConnection.createStatement();
            ResultSet transaction = statement.executeQuery(query);
            while (transaction.next()) {
                int cont_debitor = transaction.getInt("cont_debitor");
                int cont_creditor = transaction.getInt("cont_creditor");
                Date date = transaction.getDate("data_tranzactie");
                float suma = transaction.getFloat("suma_tranzactie");
                String descriere = transaction.getString("descriere_tranzactie");

                Transaction tran = new Transaction(cont_debitor, cont_creditor, date, suma, descriere);
                transactions.add(tran);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public Set getTransactionsFromDateToDate( LocalDate data_inceput, LocalDate data_sfarsit) {
        String query = "SELECT * from tranzactie";
        LocalDate start = data_inceput;
        LocalDate end = data_sfarsit;

        Set<Transaction> transactions = new HashSet<Transaction>();

        try {
            Statement statement = sqlConnection.createStatement();
            ResultSet getDatesBetweenStartEnd = statement.executeQuery("SELECT * from tranzactie where data_tranzactie BETWEEN '" + data_inceput + "' and '" + data_sfarsit + "' order by data_tranzactie DESC");
            while (getDatesBetweenStartEnd.next()) {
                int cont_debitor = getDatesBetweenStartEnd.getInt("cont_debitor");
                int cont_creditor = getDatesBetweenStartEnd.getInt("cont_creditor");
                Date date = getDatesBetweenStartEnd.getDate("data_tranzactie");
                float suma = getDatesBetweenStartEnd.getFloat("suma_tranzactie");
                String descriere = getDatesBetweenStartEnd.getString("descriere_tranzactie");

                Transaction tran = new Transaction(cont_debitor, cont_creditor, date, suma, descriere);
                transactions.add(tran);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public List getSpecificTypeTransactions( String tip) {
        String getAccounts = "SELECT * from cont where tip_cont='" + tip + "'";

        List transactions = new ArrayList();
        List<String> accountsTypes = new ArrayList<>();
        List<Integer> accountNumbers = new ArrayList<>();
        try {
            Statement statement = sqlConnection.createStatement();
            ResultSet getAccountsFromDB = statement.executeQuery(getAccounts);

            int nr_cont;
            while (getAccountsFromDB.next()) {
                String tip_cont = getAccountsFromDB.getString("tip_cont");
                nr_cont = getAccountsFromDB.getInt("nr_cont");

                //Transaction tran = new Transaction(cont_debitor, cont_creditor, date, suma, descriere);
                accountsTypes.add(tip_cont);
                accountNumbers.add(nr_cont);
            }

            for (Integer accNumber : accountNumbers) {
                ResultSet transaction = statement.executeQuery("SELECT * from tranzactie where cont_debitor='" + accNumber + "' or cont_creditor='" + accNumber + "'");
                while (transaction.next()) {
                    accountNumbers.add(accNumber);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountNumbers;
    }
    public Set getBestAccount(){
        //SELECT COUNT(`cont_debitor`) FROM `tranzactie` GROUP BY `cont_debitor`;
        String query = "SELECT *, COUNT(*) from tranzactie group by cont_debitor";
        int cont_debitorMax = -1;
        try {
            Statement statement = sqlConnection.createStatement();
            ResultSet transaction = statement.executeQuery(query);
            int cont_debitor = -1;
            int count = -1;
            int countMax = -1;
            while (transaction.next()) {
                cont_debitor = transaction.getInt("cont_debitor");
                count = transaction.getInt("COUNT(*)");

                if(count > countMax){
                    countMax = count;
                    cont_debitorMax = cont_debitor;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getTransactions(cont_debitorMax);
    }
}
