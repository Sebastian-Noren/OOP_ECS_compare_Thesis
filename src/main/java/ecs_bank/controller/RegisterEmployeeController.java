package ecs_bank.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterEmployeeController implements Initializable {

    @FXML
    private TextField firstName,lastName,ssn,street,streetNumber,city,country,registrationDate,phoneNumber,privateAccountName;
    @FXML
    private TextField privateClearingNumber,privateAccountNumber,privateIBANNumber,savingsAccountName,savingsClearingNumber;
    @FXML
    private TextField savingsAccountNumber,savingsIBANNumber;
    @FXML
    private Button createUserBtn, cancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
