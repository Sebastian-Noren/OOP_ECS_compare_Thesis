package ecs_bank.models;

import ecs_bank.models.accounts.PrivateAccount;

import java.util.Date;

public class Admin extends Employee {

    public Admin(String firstName, String lastName, String ssn, Address address, Date registrationDate, String phoneNumber, PrivateAccount privateAccount) {
        super(firstName, lastName, ssn, address, registrationDate, phoneNumber, privateAccount);
    }

}
