package ecs_bank.random_generator;

import ecs_bank.models.Address;
import ecs_bank.random_generator.address.RandomAddress;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class Generator {
    public static void main(String[] args) {

        FirstNameSource firstNameSource = new FirstNameSource();
        SurnameSource surnameSource = new SurnameSource();
        RandomAddress randomAddress = new RandomAddress();

        PersonalNumber personalNumber = createRandomPersonalNumber();


        String firstName = firstNameSource.random(personalNumber.getSerialNumber());
        String surname = surnameSource.random();
        String ssn = personalNumber.getPersonalNumber();
        String address = createRandomAddress().toString();

        System.out.println("Name: " + firstName + " " + surname + " SSN: " + ssn + " Address: " + address);

    }


    private static PersonalNumber createRandomPersonalNumber() {
        RandomPersonalNumber ssn = new RandomPersonalNumber();
        return new PersonalNumber(
                ssn.getYear(),
                ssn.getMonth(),
                ssn.getDay(),
                ssn.getSerialNumber(),
                ssn.getControlNumber());
    }

    private static Address createRandomAddress() {
        RandomAddress address = new RandomAddress();
        return new Address(
                address.getStreet(),
                address.getStreetNumber(),
                address.getZipCode(),
                address.getCity(),
                address.getCountry()
        );
    }
}
