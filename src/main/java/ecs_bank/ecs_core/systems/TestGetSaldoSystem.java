package ecs_bank.ecs_core.systems;

import ecs_bank.ecs_core.components.*;
import ecs_bank.models.accounts.Transaction;

import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TestGetSaldoSystem {


    public double processPrivateAccunt(ArrayList<PrivateAccountComponent> listPrivate) {

        double saldo = 0;
        for (PrivateAccountComponent aListPrivate : listPrivate) {
            for (TransactionComponent transaction : aListPrivate.transactions) {
                saldo += transaction.amount;
            }
        }
        return saldo;

    }


    public double processSavingsAccount(ArrayList<SavingsAccountComponent> listPrivate) {

        double saldo = 0;
        for (SavingsAccountComponent aListPrivate : listPrivate) {
            for (TransactionComponent transaction : aListPrivate.transactions) {
                saldo += transaction.amount;
            }
        }
        return saldo;

    }


}
