package ecs_bank.models;

import ecs_bank.models.accounts.PrivateAccount;

import java.util.Date;

public class Banker extends Employee {


    public Banker(String firstName, String lastName, String ssn, Address address, Date registrationDate, String phoneNumber,String password, PrivateAccount privateAccount) {
        super(firstName, lastName, ssn, address, registrationDate, phoneNumber, password, privateAccount);
    }
}
