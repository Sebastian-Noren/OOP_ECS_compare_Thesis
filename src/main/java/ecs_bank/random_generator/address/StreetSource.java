package ecs_bank.random_generator.address;

import ecs_bank.random_generator.ReadFile;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class StreetSource {

    private ArrayList<String> streets;
    private SecureRandom random = new SecureRandom();

    private static final String STREET_PATH = "src/main/resources/random_generator/address/streets.txt";

   public StreetSource() {
        streets = ReadFile.readFile(STREET_PATH);
    }


    public String random() {
        return streets.get(random.nextInt(streets.size()));
    }
}
