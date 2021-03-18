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

    public float getSaldo() {
        float saldo = 0;

        for (Transaction transaction : transactions) {
            saldo += transaction.getAmount();
        }
        return saldo;
    }

    public void withdraw(Transaction transaction) {
        this.transactions.add(transaction);

    }

    public void deposit(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
