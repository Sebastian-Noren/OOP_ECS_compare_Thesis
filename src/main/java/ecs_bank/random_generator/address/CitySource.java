package ecs_bank.random_generator.address;

import ecs_bank.random_generator.ReadFile;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CitySource {

    private ArrayList<String> cachedCities;
    private SecureRandom random = new SecureRandom();

    private static final String CITY_PATH = "src/main/resources/random_generator/address/cities.txt";

    CitySource() {
        cachedCities = ReadFile.readFile(CITY_PATH);
    }


    String random() {
        return cachedCities.get(random.nextInt(cachedCities.size()));
    }
}
