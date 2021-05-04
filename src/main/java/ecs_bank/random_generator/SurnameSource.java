package ecs_bank.random_generator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SurnameSource {

    private ArrayList<String> cachedSurnames;
    private SecureRandom random = new SecureRandom();

    private static final String SURNAMES_PATH = "src/main/resources/random_generator/person/surnames.txt";

    public SurnameSource() {
        cachedSurnames = ReadFile.readFile(SURNAMES_PATH);
    }

    public String random() {
        return cachedSurnames.get(random.nextInt(cachedSurnames.size()));
    }
}
