package ecs_bank.random_generator.address;

import java.util.concurrent.ThreadLocalRandom;

public class RandomAddress {

    private String street;
    private int streetNumber;
    private int zipCode;
    private String city;
    private String country;

    public RandomAddress() {
        this.street = new StreetSource().random();
        this.streetNumber = ThreadLocalRandom.current().nextInt(1,101);
        this.zipCode = ThreadLocalRandom.current().nextInt(10000, 97999);
        this.city = new CitySource().random();
        this.country = "Sweden";
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
