package ecs_bank.models;

public class Person {
    private String firstName;
    private String lastName;
    private String ssn;
    private Address address;

    public Person (String firstName, String lastName, String ssn){
        this.firstName = firstName;
        this.lastName  = lastName;
        this.ssn       = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
