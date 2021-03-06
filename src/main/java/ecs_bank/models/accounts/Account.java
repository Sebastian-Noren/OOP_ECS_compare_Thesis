package ecs_bank.models.accounts;

import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public abstract class Account {
    private String accountName;
    private int clearingNbr;
    private int accountNrb;
    private int IBAN;
    private ArrayList<Transaction> transactions;

    public Account(String accountName, int clearingNbr, int accountNrb, int IBAN, ArrayList<Transaction> transactions) {
        this.accountName = accountName;
        this.clearingNbr = clearingNbr;
        this.accountNrb = accountNrb;
        this.IBAN = IBAN;
        this.transactions = transactions;
    }


    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double getSaldo() {
        float saldo = 0;

        for (Transaction transaction : transactions) {
            saldo += transaction.getAmount();
        }
        return saldo;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);

    }


    public String printTransactions(){
        return transactions.toString();
    }

    public String getAccountName() {
        return accountName;
    }

    public int getClearingNbr() {
        return clearingNbr;
    }

    public int getAccountNrb() {
        return accountNrb;
    }

    public int getIBAN() {
        return IBAN;
    }

    @Override
    public String toString() {
        return
                "Account Name: " + accountName + '\n' +
                "Clearing Number: " + clearingNbr +  '\n' +
                "Account Number: " + accountNrb + '\n' +
                "IBAN Number: " + IBAN + '\n' +
                "========= Transaction(s) ========== \n " + transactions.toString() + '\n';
    }
}
