package ecs_bank.random_generator;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class FirstNameSource {

    private ArrayList<String> cachedFemaleNames;
    private ArrayList<String> cachedMaleNames;

    private static final String FEMALE_PATH = "src/main/resources/random_generator/person/female_names.txt";
    private static final String MALE_PATH = "src/main/resources/random_generator/person/male_names.txt";

    FirstNameSource() {
        cachedFemaleNames = ReadFile.readFile(FEMALE_PATH);
        cachedMaleNames = ReadFile.readFile(MALE_PATH);
    }


    public String random(int serialNumber) {
        if (isFemale(serialNumber)) {
            return cachedFemaleNames.get(ThreadLocalRandom.current().nextInt(cachedFemaleNames.size()));
        } else {
            return cachedMaleNames.get(ThreadLocalRandom.current().nextInt(cachedMaleNames.size()));
        }
    }

    private boolean isFemale(int serialNumber) {
        return serialNumber % 2 == 0;
    }

}
