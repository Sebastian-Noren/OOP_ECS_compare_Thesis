package ecs_bank.random_generator;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDate {



    public static LocalDate getRandomDate(int year) {
        LocalDate start = LocalDate.of(year, Month.JANUARY, 1);
        LocalDate end = LocalDate.now();
        long startEpochDay = start.toEpochDay();
        long endEpochDay = end.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}
