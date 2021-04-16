package ecs_bank.models.accounts;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class Transaction {
    private String description;
    private LocalDate transactionDate;
    private double amount;

    public Transaction(String description, LocalDate transactionDate, double amount) {
        this.description = description;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Description: " + description + '\n' +
                "Transaction Date: " + transactionDate + '\n' +
                "Amount: " + amount + " kr" + "\n------------------------------------------------------\n";

    }
}


