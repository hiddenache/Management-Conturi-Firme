package database;

import Storage.Transaction;
import com.example.Otherss.Alertt;
import com.example.Otherss.Suma;
import javafx.css.Size;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseOperations {

    private String result;

    public Connection sqlConnection;
    private final Alertt alertt = new Alertt();

    public DatabaseOperations(Connection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }


    /**
     * Functie pentru preluarea de informatii dintr-un anumit tabel
     *
     * @params query - comanda care trebuie sa se execute
     * text - textul care trebuie modificat in momentul cand comanda returneaza o informatie
     * columnLabel - coloana din baza de date de care avem nevoie pentru a retrage informatii
     */
    public String getSoldCurent(String query, String columnLabel) {
        String result = "";
        try {
            Statement statement = sqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            result = resultSet.getString(columnLabel);
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    public int deleteAccount(String accNum) {

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

    public boolean checkIfAccExists(String accNum) {
        String queryOp = "SELECT * from cont WHERE nr_cont='" + accNum + "'";
        try {
            Statement statement = sqlConnection.createStatement();
            ResultSet result = statement.executeQuery(queryOp);
            if (result.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int addAccount(String numeCont, String descriere, String tipCont, float sold_initial) {

        String queryOp = "INSERT into cont (nume_cont, descriere, tip_cont, sold_initial, sold_curent)" +
                "VALUES ('" + numeCont + "','" + descriere + "','" + tipCont + "','" + sold_initial + "', '" + sold_initial + "')";

        try {
            Statement statement = sqlConnection.createStatement();
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

    public void newTransaction(int cont_creditor, int cont_debitor, Float suma, String descriere) {
        java.util.Date javaDate = new java.util.Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());

        float soldFinalCreditor; // cel care primeste
        float soldFinalDebitor; // cel care trimite

        Alertt alert = new Alertt();

        if (cont_creditor != cont_debitor) { // contul care trimite nu trebuie sa fie la fel cu cel care primeste
            try {
                Statement statement = sqlConnection.createStatement();

                ResultSet validate_cont = statement.executeQuery("SELECT * from cont");

                ResultSet soldContCreditor = statement.executeQuery("SELECT * from cont where nr_cont='" + cont_creditor + "'"); // cel care primeste
                soldContCreditor.next();
                soldFinalCreditor = soldContCreditor.getFloat("sold_curent") + suma;

                ResultSet soldContDebitor = statement.executeQuery("SELECT * from cont where nr_cont='" + cont_debitor + "'"); // cel care trimite
                soldContDebitor.next();

                soldFinalDebitor = soldContDebitor.getFloat("sold_curent") - suma;

                if (soldFinalDebitor < 0 || suma > soldFinalDebitor - suma) {
                    // soldFinalCreditor = sold curent + suma de tranzactionare
                    // verificam daca suma de tranzactionare este mai mare decat soldul curent
                    alert.createInformationAlert("NEGATIVE");
                } else {
                    if (suma < 10000 && suma > 0) {
                        String insertSoldFinalCreditor = "UPDATE cont SET sold_curent='" + soldFinalCreditor + "' where nr_cont='" + cont_creditor + "'";
                        String insertSoldFinalDebitor = "UPDATE cont SET sold_curent='" + soldFinalDebitor + "' where nr_cont='" + cont_debitor + "'";

                        statement.executeUpdate(insertSoldFinalCreditor);
                        statement.executeUpdate(insertSoldFinalDebitor);


                        String newTransactionInfo = "INSERT into tranzactie (cont_debitor, cont_creditor, data_tranzactie, suma_tranzactie, descriere_tranzactie)" +
                                "VALUES ('" + cont_debitor + "','" + cont_creditor + "','" + mySQLDate + "','" + suma + "','" + descriere + "')";

                        statement.executeUpdate(newTransactionInfo);
                        alertt.createInformationAlert("TRANZACTIE");

                    } else {
                        alertt.createInformationAlert("NEGATIVE-TRANSACTION");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            alert.createInformationAlert("SAMEACC");
        }

    }

    public Set getTransactions(int nr_cont) {
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

    public Set getTransactionsFromDateToDate(LocalDate data_inceput, LocalDate data_sfarsit) {
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

    public List getSpecificTypeTransactions(String tip) {
        String getAccounts = "SELECT * from cont where tip_cont='" + tip + "'";

        List transactions = new ArrayList();
        List<String> accountsTypes = new ArrayList<>();
        List<Integer> accountNumbers = new ArrayList<>();
        List<Integer> accountFinalIds = new ArrayList<>();
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
                    accountFinalIds.add(accNumber);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountFinalIds;
    }

    public Set getBestAccount() {
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

                if (count > countMax) {
                    countMax = count;
                    cont_debitorMax = cont_debitor;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getTransactions(cont_debitorMax);
    }

    public ArrayList<Suma> getBilant() {
        ArrayList<Suma> listSuma = new ArrayList<>();
        int nr_cont;
        float sold_initial;
        float suma_creditoare;
        float suma_debitoare;
        ResultSet rsContC, rsContD;
        String query = "SELECT nr_cont, sold_initial FROM cont ";
        try {
            Statement statement = sqlConnection.createStatement();
            Statement statementC = sqlConnection.createStatement();
            Statement statementD = sqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                suma_creditoare = 0;
                suma_debitoare = 0;
                nr_cont = resultSet.getInt("nr_cont");
                sold_initial = resultSet.getFloat("sold_initial");

                rsContC = statementC.executeQuery("SELECT suma_tranzactie FROM tranzactie WHERE cont_creditor = " + nr_cont);
                while (rsContC.next()) suma_creditoare += rsContC.getFloat("suma_tranzactie");
                rsContC.close();

                rsContD = statementD.executeQuery("SELECT suma_tranzactie FROM tranzactie WHERE cont_debitor = " + nr_cont);
                while (rsContD.next()) suma_debitoare += rsContD.getFloat("suma_tranzactie");
                rsContD.close();

                listSuma.add(new Suma(nr_cont, sold_initial, suma_creditoare, suma_debitoare));
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listSuma;

    }

    public void delete() {
        ArrayList<Suma> listSuma = getBilant();
        String query;
        int ok = 0;

        try {
            Statement statement = sqlConnection.createStatement();
            for (Suma suma : listSuma) {
                if (suma.getSumaCreditoare() == 0 && suma.getSumaDebitoare() == 0) {
                    ok = 1;
                    query = "DELETE from cont WHERE nr_cont = " + suma.getNumarCont();
                    statement.executeUpdate(query);
                }
            }
            if (ok == 1) alertt.createInformationAlert("DELETE_OK");
            else alertt.createInformationAlert("DELETE_INFO");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
