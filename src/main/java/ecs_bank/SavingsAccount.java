package ecs_bank;

import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class SavingsAccount extends Account{
    public SavingsAccount(String accountName, int clearingNbr, int accountNrb, int IBAN, ArrayList<Transaction> transactions) {
        super(accountName, clearingNbr, accountNrb, IBAN, transactions);
    }
}
