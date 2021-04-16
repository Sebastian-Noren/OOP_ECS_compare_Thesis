package ecs_bank.random_generator;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import static ecs_bank.random_generator.RandomDate.getRandomDate;

public class RandomTransaction {
    private SecureRandom random = new SecureRandom();

    private String[] income = {"Salary", "CSN", "Government funding", "Swish", "Other"};
    private String[] expense = {"Food", "Movies", "Car", "Toys", "Makeup", "Fast food", "Training fee", "Present", "Rent", "Other"};

    public RandomTransaction() {
    }

    public String getDescription(int chance) {
        if (chance < 20) { // 20%
            return income[random.nextInt(income.length)];
        }
        return expense[random.nextInt(expense.length)];
    }

    public LocalDate getTransactionDate(int year) {
        return getRandomDate(year);
    }

    public double getAmount(int chance) {
        if (chance < 20) { // 20%
            return ThreadLocalRandom.current().nextInt(1, 4000);
        }
        int value = ThreadLocalRandom.current().nextInt(1, 1000);
        return -value;
    }
}
