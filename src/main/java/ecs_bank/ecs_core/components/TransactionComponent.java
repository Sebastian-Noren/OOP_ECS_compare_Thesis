package ecs_bank.ecs_core.components;

import java.time.LocalDate;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TransactionComponent implements IComponent{
    public String description;
    public LocalDate transactionDate;
    public double amount;
}
