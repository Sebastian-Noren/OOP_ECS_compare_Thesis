package ecs_bank.controller;
import ecs_bank.AppConstants;
import ecs_bank.models.Address;


import ecs_bank.models.Banker;
import ecs_bank.models.Customer;
import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.Transaction;
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
    private TextArea textArea;
    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        tableOnClick();
        insertUsersToTable();
    }

    public void tableOnClick(){
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

    public void initializeTable(){
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
        tableView.getColumns().addAll(firstNameColumn,lastNameColumn,ssn);
    }

    public void searchByClick(int i){
        clearTextArea();
        Customer customer = AppConstants.getInstance().getCustomers().get(i);

        textArea.appendText("First Name: " + customer.getFirstName() +"\n");
        textArea.appendText("Last Name: "  + customer.getLastName() +"\n");
        textArea.appendText("Social Security Number: " + customer.getSsn() +"\n");
        textArea.appendText("Street: " + customer.getAddress().getStreet() +"\n");
        textArea.appendText("Street Number: " + customer.getAddress().getStreetNumber() +"\n");
        textArea.appendText("City: " + customer.getAddress().getCity() +"\n");
        textArea.appendText("Country: " + customer.getAddress().getCountry() +"\n");
        textArea.appendText("Registration Date: " + customer.getRegistrationDate() +"\n");
        textArea.appendText("Phone Number: " + customer.getPhoneNumber() +"\n");
        textArea.appendText("========= Private Account ========= \n");
        textArea.appendText(customer.getPrivateAccount().toString());
        textArea.appendText("========= Saving Account(s) ========= \n");
        textArea.appendText(customer.getSavingsAccountList().toString() +"\n");

    }
    public void searchTable(){
        clearTextArea();
        String reqSSN = searchField.getText();
        for(Customer customers: AppConstants.getInstance().getCustomers()){
            if(reqSSN.equals(customers.getSsn())){
                textArea.appendText("First Name: " + customers.getFirstName() +"\n");
                textArea.appendText("Last Name: "  + customers.getLastName() +"\n");
                textArea.appendText("Social Security Number: " + customers.getSsn() +"\n");
                textArea.appendText("Street: " + customers.getAddress().getStreet() +"\n");
                textArea.appendText("Street Number: " + customers.getAddress().getStreetNumber() +"\n");
                textArea.appendText("City: " + customers.getAddress().getCity() +"\n");
                textArea.appendText("Country: " + customers.getAddress().getCountry() +"\n");
                textArea.appendText("Registration Date: " + customers.getRegistrationDate() +"\n");
                textArea.appendText("Phone Number: " + customers.getPhoneNumber() +"\n");
                textArea.appendText("========= Private Account ========= \n");
                textArea.appendText(customers.getPrivateAccount().toString());
                textArea.appendText("========= Saving Account(s) ========= \n");
                textArea.appendText(customers.getSavingsAccountList().toString() +"\n");
                return;
            }
        }
        textArea.setText("THERE IS NO USER WITH THAT SSN! TRY AGAIN");
    }

    public void insertUsersToTable(){
        for(Customer customer :  AppConstants.getInstance().getCustomers()){
            tableView.getItems().add(customer);
        }
    }

    public void clearTable(){
        tableView.getItems().clear();
    }
    public void clearTextArea(){
        textArea.clear();
    }



}
