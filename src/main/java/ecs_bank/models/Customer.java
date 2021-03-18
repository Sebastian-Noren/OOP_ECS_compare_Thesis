package ecs_bank.models;

import java.util.ArrayList;

public class Customer extends Person {
    private String registrationDate;
   // private PrivateAccount privateAccount;
   // private List<SavingsAccount> savingsAccountList;
    private String phoneNumber;


    public Customer(String firstName, String lastName, String ssn, Address address, String registrationDate, String phoneNumber) {
        super(firstName, lastName, ssn, address);
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;

      // this.List<SavingsAccount> savingsAccountList = new ArrayList<>();
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    /*
        public PrivateAccount getPrivateAccount() {
            return privateAccount;
        }

        public void setPrivateAccount(PrivateAccount privateAccount) {
            this.privateAccount = privateAccount;
        }

        public List<SavingsAccount> getSavingsAccountList() {
            return savingsAccountList;
        }

        public void setSavingsAccountList(List<SavingsAccount> savingsAccountList) {
            this.savingsAccountList = savingsAccountList;
        }
    */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void transferMoney(String fromAccount, String toAccount){

    }
    private void deleteSavingsAccount(String accountNbr){

    }
}
