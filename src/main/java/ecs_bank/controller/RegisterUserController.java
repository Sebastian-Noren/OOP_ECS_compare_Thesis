package ecs_bank.controller;

import ecs_bank.AppConstants;
import ecs_bank.models.Address;
import ecs_bank.models.Customer;
import ecs_bank.models.accounts.PrivateAccount;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterUserController implements Initializable {

    @FXML
    private TextField firstName,lastName,ssn,street,streetNumber,city,country,phoneNumber,privateAccountName;
    @FXML
    private TextField privateClearingNumber,privateAccountNumber,privateIBANNumber,zipcode,password;

    @FXML
    private TextArea textArea;

    @FXML
    private CheckBox privateAccountCheckbox;

    @FXML
    private Button createUserBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindTextFieldsToCreateBtn();
        privateAccountCheckbox.setSelected(true);
        writeToTextArea("Welcome!\nFill out all the information and create user!");

    }

    public void registerUser(){
        clearTextArea();

        //Address information
        Address address = new Address(street.getText(),Integer.parseInt(streetNumber.getText()),Integer.parseInt(zipcode.getText()),city.getText(),country.getText());
        //Private account information
        PrivateAccount privateAccount = new PrivateAccount(privateAccountName.getText(),Integer.parseInt(privateClearingNumber.getText()),Integer.parseInt(privateAccountNumber.getText()),Integer.parseInt(privateIBANNumber.getText()), new ArrayList<>());
        //Optional savings account
        Customer customer = new Customer(firstName.getText(), lastName.getText(), ssn.getText(), address, LocalDate.now(),
                phoneNumber.getText(), password.getText(), privateAccount);

        AppConstants.getInstance().getCustomerMap().put(customer.getSsn(),customer);
        AppConstants.getInstance().getCustomers().add(customer);

        writeToTextArea("The user: " + firstName.getText() + " was successfully created!");

    }

    public void bindTextFieldsToCreateBtn(){
        createUserBtn.disableProperty().bind(
                Bindings.isEmpty(firstName.textProperty())
                        .or(Bindings.isEmpty(lastName.textProperty()))
                        .or(Bindings.isEmpty(ssn.textProperty()))
                        .or(Bindings.isEmpty(street.textProperty()))
                        .or(Bindings.isEmpty(streetNumber.textProperty()))
                        .or(Bindings.isEmpty(city.textProperty()))
                        .or(Bindings.isEmpty(country.textProperty()))
                        .or(Bindings.isEmpty(phoneNumber.textProperty()))
                        .or(Bindings.isEmpty(privateAccountName.textProperty()))
                        .or(Bindings.isEmpty(privateClearingNumber.textProperty()))
                        .or(Bindings.isEmpty(privateAccountNumber.textProperty()))
                        .or(Bindings.isEmpty(privateIBANNumber.textProperty())));
    }

    public void cancel(){
        firstName.clear();
        lastName.clear();
        ssn.clear();
        street.clear();
        streetNumber.clear();
        city.clear();
        country.clear();
        phoneNumber.clear();
        zipcode.clear();
        privateAccountName.clear();
        privateClearingNumber.clear();
        privateAccountNumber.clear();
        privateIBANNumber.clear();
        password.clear();

    }

    private void writeToTextArea(String text){
        textArea.setText(text);
    }

    private void clearTextArea(){
        textArea.clear();
    }
}
