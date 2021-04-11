package ecs_bank.random_generator.address;

import ecs_bank.random_generator.ReadFile;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class StreetSource {

    private ArrayList<String> streets;

    private static final String STREET_PATH = "src/main/resources/random_generator/address/streets.txt";

    StreetSource() {
        streets = ReadFile.readFile(STREET_PATH);
    }


    String random() {
        return streets.get(ThreadLocalRandom.current().nextInt(streets.size()));
    }
}
