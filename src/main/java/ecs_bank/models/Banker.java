package ecs_bank.models;

public class Banker extends Employee {


    public Banker(String firstName, String lastName, String ssn, Address address, String registrationDate, String phoneNumber) {
        super(firstName, lastName, ssn,address, registrationDate, phoneNumber);
    }
}
