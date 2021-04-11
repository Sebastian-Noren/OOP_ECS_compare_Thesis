package ecs_bank.models;

import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.SavingsAccount;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends Person {
    private Date registrationDate;
    private PrivateAccount privateAccount;
    private ArrayList<SavingsAccount> savingsAccountList;
    private String phoneNumber;
    private String passWord;


    public Customer(String firstName, String lastName, String ssn, Address address, Date registrationDate, String phoneNumber, String password, PrivateAccount privateAccount) {
        super(firstName, lastName, ssn, address);
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.privateAccount = privateAccount;
        this.savingsAccountList = new ArrayList<>();
        this.passWord = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }


    public PrivateAccount getPrivateAccount() {
        return privateAccount;
    }

    public void setPrivateAccount(PrivateAccount privateAccount) {
        this.privateAccount = privateAccount;
    }

    public ArrayList<SavingsAccount> getSavingsAccountList() {
        return savingsAccountList;
    }

    public void setSavingsAccountList(ArrayList<SavingsAccount> savingsAccountList) {
        this.savingsAccountList = savingsAccountList;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void transferMoney(String fromAccount, String toAccount) {

    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    private void deleteSavingsAccount(String accountNbr) {

    }


}
