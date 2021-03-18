package ecs_bank.models;

public abstract class Employee extends Customer {

    public Employee(String firstName, String lastName, String ssn, Address address, String registrationDate, String phoneNumber) {
        super(firstName, lastName, ssn,address, registrationDate, phoneNumber);
    }

}
