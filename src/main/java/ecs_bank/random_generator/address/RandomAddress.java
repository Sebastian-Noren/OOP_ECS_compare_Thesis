package ecs_bank.random_generator.address;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomAddress {
    private String country;

   private StreetSource streetSource = new StreetSource();
   private CitySource citySource = new CitySource();
    private SecureRandom random = new SecureRandom();

    public RandomAddress() {
        streetSource = new StreetSource();
        citySource = new CitySource();
        this.country = "Sweden";
    }

    public int getStreetNumber() {
        return random.nextInt(101) + 1;
    }

    public String getStreet() {
        return streetSource.random();
    }

    public int getZipCode() {
        return random.nextInt(97999) + 10000;
    }

    public String getCity() {
        return citySource.random();
    }

    public String getCountry() {
        return country;
    }
}
