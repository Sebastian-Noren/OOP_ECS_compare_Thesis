package ecs_bank.controller;

import ecs_bank.AppConstants;
import ecs_bank.models.Address;
import ecs_bank.models.Admin;
import ecs_bank.models.Banker;
import ecs_bank.models.accounts.PrivateAccount;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterEmployeeController implements Initializable {

    @FXML
    private TextField firstName,lastName,ssn,street,streetNumber,city,country,phoneNumber,privateAccountName;
    @FXML
    private TextField privateClearingNumber,privateAccountNumber,privateIBANNumber,zipcode,password;

    @FXML
    private TextArea textArea;

    @FXML
    private CheckBox adminCheckbox,bankerCheckbox,privateAccountCheckbox;

    @FXML
    private Button createUserBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindTextFieldsToCreateBtn();
        privateAccountCheckbox.setSelected(true);
        writeToTextArea("Welcome!\nFill out all the information and create user!");
    }

    public void createEmployee(){
        clearTextArea();

        if(bankerCheckbox.isSelected() && adminCheckbox.isSelected()){
            writeToTextArea("Select only 1 employee type!");
        } else if(bankerCheckbox.isSelected()){
            Banker banker = new Banker(firstName.getText(),lastName.getText(),ssn.getText(),
                    new Address(street.getText(),Integer.parseInt(streetNumber.getText()),Integer.parseInt(zipcode.getText()),
                            city.getText(),country.getText()),new Date() ,phoneNumber.getText(), password.getText(),
                    new PrivateAccount(privateAccountName.getText(),Integer.parseInt(privateClearingNumber.getText()),Integer.parseInt(privateAccountNumber.getText()),
                            Integer.parseInt(privateIBANNumber.getText()),new ArrayList<>()));

            AppConstants.getInstance().getCustomers().add(banker);
            AppConstants.getInstance().getCustomerMap().put(ssn.getText(),banker);

            writeToTextArea("The Banker: " + firstName.getText() + " was successfully created!");
        } else if(adminCheckbox.isSelected()){
            Admin admin = new Admin(firstName.getText(),lastName.getText(),ssn.getText(),
                    new Address(street.getText(),Integer.parseInt(streetNumber.getText()),Integer.parseInt(zipcode.getText()),
                            city.getText(),country.getText()),new Date() ,phoneNumber.getText(), password.getText(),
                    new PrivateAccount(privateAccountName.getText(),Integer.parseInt(privateClearingNumber.getText()),Integer.parseInt(privateAccountNumber.getText()),
                            Integer.parseInt(privateIBANNumber.getText()),new ArrayList<>()));

            AppConstants.getInstance().getCustomers().add(admin);
            AppConstants.getInstance().getCustomerMap().put(ssn.getText(),admin);

            writeToTextArea("The Admin: " + firstName.getText() + " was successfully created!");
        }else{
            writeToTextArea("Please choose employee type!");
        }

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

    private void writeToTextArea(String text){
        textArea.setText(text);
    }

    private void clearTextArea(){
        textArea.clear();
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
       privateAccountName.clear();
       adminCheckbox.setSelected(false);
       bankerCheckbox.setSelected(false);
    }
}
