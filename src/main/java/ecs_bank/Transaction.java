package ecs_bank;

import java.util.Date;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class Transaction {
    private String description;
    private Date transactionDate;
    private double amount;

    public Transaction(String description, Date transactionDate, double amount) {
        this.description = description;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                '}';
    }
}


