package ecs_bank.controller;

import ecs_bank.AppConstants;
import ecs_bank.models.Address;


import ecs_bank.models.Banker;
import ecs_bank.models.Customer;
import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.Transaction;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private TableView tableView;
    @FXML
    private TextArea textArea, statusArea;
    @FXML
    private TextField searchField;

    //information for registration
    @FXML
    private TextField firstName,lastName,ssn,phoneNumber,country,city,street,
            streetNumber,zipcode,accountName,clearingNumber,accountNumber,IBANNumber,password;

     @FXML
    private Button createCustomerBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        tableOnClick();
        insertUsersToTable();
        bindTextFieldsToCreateBtn();
    }

    public void tableOnClick() {
        tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    Node node = ((Node) event.getTarget()).getParent();
                    TableRow row;
                    if (node instanceof TableRow) {
                        row = (TableRow) node;
                    } else {
                        // clicking on text part
                        row = (TableRow) node.getParent();
                    }
                    searchByClick(row.getIndex());
                }
            }
        });
    }

    public void initializeTable() {
        //Set table rows
        //first name
        TableColumn firstNameColumn = new TableColumn("First Name"); //How it should be presented in table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName")); //The variable name from its class
        //last name
        TableColumn lastNameColumn = new TableColumn("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        //ssn
        TableColumn ssn = new TableColumn("SSN");
        ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        //insert into table
        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ssn);
    }

    public void searchByClick(int i) {
        clearTextArea();
        Customer customer = AppConstants.getInstance().getCustomers().get(i);

        textArea.appendText("First Name: " + customer.getFirstName() + "\n");
        textArea.appendText("Last Name: " + customer.getLastName() + "\n");
        textArea.appendText("Social Security Number: " + customer.getSsn() + "\n");
        textArea.appendText("Street: " + customer.getAddress().getStreet() + "\n");
        textArea.appendText("Street Number: " + customer.getAddress().getStreetNumber() + "\n");
        textArea.appendText("City: " + customer.getAddress().getCity() + "\n");
        textArea.appendText("Country: " + customer.getAddress().getCountry() + "\n");
        textArea.appendText("Registration Date: " + customer.getRegistrationDate() + "\n");
        textArea.appendText("Phone Number: " + customer.getPhoneNumber() + "\n");
        textArea.appendText("========= Private Account ========= \n");
        textArea.appendText(customer.getPrivateAccount().toString());
        textArea.appendText("========= Saving Account(s) ========= \n");
        textArea.appendText(customer.getSavingsAccountList().toString() + "\n");

    }

    public void searchTable() {
        clearTextArea();
        String reqSSN = searchField.getText().trim();

        Customer customer = AppConstants.getInstance().getCustomerMap().get(reqSSN);

        if (customer != null) {
            textArea.appendText("First Name: " + customer.getFirstName() + "\n");
            textArea.appendText("Last Name: " + customer.getLastName() + "\n");
            textArea.appendText("Social Security Number: " + customer.getSsn() + "\n");
            textArea.appendText("Street: " + customer.getAddress().getStreet() + "\n");
            textArea.appendText("Street Number: " + customer.getAddress().getStreetNumber() + "\n");
            textArea.appendText("City: " + customer.getAddress().getCity() + "\n");
            textArea.appendText("Country: " + customer.getAddress().getCountry() + "\n");
            textArea.appendText("Registration Date: " + customer.getRegistrationDate() + "\n");
            textArea.appendText("Phone Number: " + customer.getPhoneNumber() + "\n");
            textArea.appendText("========= Private Account ========= \n");
            textArea.appendText(customer.getPrivateAccount().toString());
            textArea.appendText("========= Saving Account(s) ========= \n");
            textArea.appendText(customer.getSavingsAccountList().toString() + "\n");
            return;
        }

        textArea.setText("THERE IS NO USER WITH THAT SSN! TRY AGAIN");
    }

    public void insertUsersToTable() {
        //clear table so one do not load in same users again - used for when creating new users and displaying them with old data
        tableView.getItems().clear();
        for (Customer customer : AppConstants.getInstance().getCustomers()) {
            tableView.getItems().add(customer);
        }

    }

    public void addCustomer(){
        //Address information
        Address address = new Address(street.getText(),Integer.parseInt(streetNumber.getText()),Integer.parseInt(zipcode.getText()),city.getText(),country.getText());
       //Private account information
        PrivateAccount privateAccount = new PrivateAccount(accountName.getText(),Integer.parseInt(clearingNumber.getText()),Integer.parseInt(accountNumber.getText()),Integer.parseInt(IBANNumber.getText()), new ArrayList<>());
        //Optional savings account
        Customer customer = new Customer(firstName.getText(), lastName.getText(), ssn.getText(), address,new Date(),
                phoneNumber.getText(), password.getText(), privateAccount);

        AppConstants.getInstance().getCustomerMap().put(customer.getSsn(),customer);
        AppConstants.getInstance().getCustomers().add(customer);

        clearCustomerFields();
        statusArea.appendText("The user: " + firstName.getText() + " was successfully created! ");
    }
    public void clearCustomerFields(){
        firstName.clear();
        lastName.clear();
        ssn.clear();
        phoneNumber.clear();
        country.clear();
        city.clear();
        street.clear();
         streetNumber.clear();
        zipcode.clear();
         accountName.clear();
        clearingNumber.clear();
        accountNumber.clear();
        IBANNumber.clear();
        password.clear();
    }
    public void bindTextFieldsToCreateBtn(){
        createCustomerBtn.disableProperty().bind(
                Bindings.isEmpty(firstName.textProperty())
                .or(Bindings.isEmpty(lastName.textProperty()))
                .or(Bindings.isEmpty(ssn.textProperty()))
                .or(Bindings.isEmpty(phoneNumber.textProperty()))
                .or(Bindings.isEmpty(country.textProperty()))
                .or(Bindings.isEmpty(city.textProperty()))
                .or(Bindings.isEmpty(street.textProperty()))
                .or(Bindings.isEmpty(streetNumber.textProperty()))
                .or(Bindings.isEmpty(zipcode.textProperty()))
                .or(Bindings.isEmpty(accountName.textProperty()))
                .or(Bindings.isEmpty(clearingNumber.textProperty()))
                .or(Bindings.isEmpty(accountNumber.textProperty()))
                .or(Bindings.isEmpty(IBANNumber.textProperty()))
                .or(Bindings.isEmpty(password.textProperty())));

    }

    public void clearTable() {
        tableView.getItems().clear();
    }

    public void clearTextArea() {
        textArea.clear();
    }


}
