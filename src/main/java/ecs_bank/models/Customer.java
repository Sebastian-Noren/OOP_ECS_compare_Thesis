package ecs_bank.models;

import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.SavingsAccount;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends Person {
    private LocalDate registrationDate;
    private PrivateAccount privateAccount;
    private ArrayList<SavingsAccount> savingsAccountList;
    private String phoneNumber;
    private String passWord;


    public Customer(String firstName, String lastName, String ssn, Address address, LocalDate registrationDate, String phoneNumber, String password, PrivateAccount privateAccount) {
        super(firstName, lastName, ssn, address);
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.privateAccount = privateAccount;
        this.savingsAccountList = new ArrayList<>();
        this.passWord = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
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

    public void addSavingsAccountToList(SavingsAccount savingsAccount){
        this.savingsAccountList.add(savingsAccount);
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


    @Override
    public String toString() {
        return "Customer{" +
                "registrationDate=" + registrationDate +
                ", privateAccount=" + privateAccount +
                ", savingsAccountList=" + savingsAccountList +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
