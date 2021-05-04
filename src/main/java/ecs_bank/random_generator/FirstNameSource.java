package ecs_bank.random_generator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class FirstNameSource {

    private ArrayList<String> cachedFemaleNames;
    private ArrayList<String> cachedMaleNames;
    private SecureRandom random = new SecureRandom();

    private static final String FEMALE_PATH = "src/main/resources/random_generator/person/female_names.txt";
    private static final String MALE_PATH = "src/main/resources/random_generator/person/male_names.txt";

    public FirstNameSource() {
        cachedFemaleNames = ReadFile.readFile(FEMALE_PATH);
        cachedMaleNames = ReadFile.readFile(MALE_PATH);
    }


    public String random(int serialNumber) {
        if (isFemale(serialNumber)) {
            return cachedFemaleNames.get(random.nextInt(cachedFemaleNames.size()));
        } else {
            return cachedMaleNames.get(random.nextInt(cachedMaleNames.size()));
        }
    }

    private boolean isFemale(int serialNumber) {
        return serialNumber % 2 == 0;
    }

}
