package ecs_bank.random_generator;

import java.time.LocalDate;

public class PersonalNumber {

    private LocalDate birthDate;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private int serialNumber;
    private int controlNumber;

    public PersonalNumber(int birthYear, int birthMonth, int birthDay, int serialNumber, int controlNumber) {
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.serialNumber = serialNumber;
        this.controlNumber = controlNumber;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getControlNumber() {
        return controlNumber;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getPersonalNumber(){
        if (birthMonth < 10 && birthDay < 10){
            return "" + birthYear + "0" + birthMonth + "0" + birthDay + "-" + serialNumber + "" + controlNumber;
        }
        else if (birthDay < 10){
            return "" + birthYear + "" + birthMonth + "0" + birthDay + "-" + serialNumber + "" + controlNumber;
        }
        else if (birthMonth < 10){
            return "" + birthYear + "0" + birthMonth + "" + birthDay + "-" + serialNumber + "" + controlNumber;
        }
        return "" + birthYear + "" + birthMonth + "" + birthDay + "-" + serialNumber + "" + controlNumber;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setControlNumber(int controlNumber) {
        this.controlNumber = controlNumber;
    }
}
