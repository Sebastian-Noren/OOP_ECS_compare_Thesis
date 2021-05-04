package ecs_bank.ecs_core.components;

import java.time.LocalDate;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TransactionComponent implements IComponent{
    public String description;
    public LocalDate transactionDate;
    public double amount;

    public TransactionComponent(String description, LocalDate transactionDate, double amount) {
        this.description = description;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }
}
