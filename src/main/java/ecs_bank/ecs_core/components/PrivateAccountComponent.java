package ecs_bank.ecs_core.components;

import ecs_bank.models.accounts.Transaction;

import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class PrivateAccountComponent implements IComponent{
    public String accountName;
    public int clearingNbr;
    public int accountNrb;
    public int IBAN;
    public ArrayList<TransactionComponent> transactions;

    public PrivateAccountComponent(String accountName, int clearingNbr, int accountNrb, int IBAN, ArrayList<TransactionComponent> transactions) {
        this.accountName = accountName;
        this.clearingNbr = clearingNbr;
        this.accountNrb = accountNrb;
        this.IBAN = IBAN;
        this.transactions = transactions;
    }
}
