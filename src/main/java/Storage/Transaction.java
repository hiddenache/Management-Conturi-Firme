package Storage;

import java.sql.Date;

public class Transaction {
    private final int cont_debitor;
    private final int cont_creditor;
    private final Date data_tranzactie;
    private final float suma_tranzactie;
    private final String descriere;


    public Transaction(int cont_debitor, int cont_creditor, Date data_tranzactie, float suma_tranzactie, String descriere) {
        this.cont_debitor = cont_debitor;
        this.cont_creditor = cont_creditor;
        this.data_tranzactie = data_tranzactie;
        this.suma_tranzactie = suma_tranzactie;
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return "Transaction: " + " FROM ACCOUNT ID=" + cont_debitor + " => TO=" + cont_creditor + " | DATE=" + data_tranzactie + " | AMOUNT=" + suma_tranzactie + "$ | DETAILS=" + descriere;
    }
}
