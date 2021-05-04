package ecs_bank.models;

import ecs_bank.models.accounts.PrivateAccount;

import java.time.LocalDate;
public class Banker extends Employee {

    private final boolean canDeleteCustomer = true;


    public Banker(String firstName, String lastName, String ssn, Address address, LocalDate registrationDate, String phoneNumber, String password, PrivateAccount privateAccount) {
        super(firstName, lastName, ssn, address, registrationDate, phoneNumber, password, privateAccount);
    }

    public boolean isCanDeleteCustomer() {
        return canDeleteCustomer;
    }

    public void deleteCustomer(){

    }
}
