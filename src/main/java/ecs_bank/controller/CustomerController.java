package ecs_bank.controller;

import ecs_bank.AppConstants;
import ecs_bank.models.Customer;
import ecs_bank.models.accounts.SavingsAccount;
import ecs_bank.models.accounts.Transaction;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private TextField accountName,clearingNumber,accountNumber,IBANNumber,withdrawField,depositField;

    @FXML
    private Button createSavingsAccountBtn;

    @FXML
    private TextArea textArea;

    @FXML
    private Label firstNameLabel,lastNameLabel,ssnLabel,phoneNumberLabel,streetLabel,streetNumberLabel,zipcodeLabel,cityLabel,countryLabel,
            accountNameLabel,clearingNumberLabel,accountNumberLabel,IBANNumberLabel, totalTransactionsLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindTextFieldsToCreateBtn();
        populatePersonalInformation();
        populatePrivateAccountInformation();
    }

    private void populatePersonalInformation(){
        Customer current = AppConstants.getInstance().getLoggedInUser();
        firstNameLabel.setText(current.getFirstName());
        lastNameLabel.setText(current.getLastName());
        ssnLabel.setText(current.getSsn());
        phoneNumberLabel.setText(current.getPhoneNumber());
        streetLabel.setText(current.getAddress().getStreet());
        streetNumberLabel.setText( String.valueOf((current.getAddress().getStreetNumber())));
        zipcodeLabel.setText(String.valueOf((current.getAddress().getZipCode())));
        cityLabel.setText(String.valueOf((current.getAddress().getCity())));
        countryLabel.setText(current.getAddress().getCountry());

    }

    private void populatePrivateAccountInformation(){
        Customer current = AppConstants.getInstance().getLoggedInUser();
        accountNameLabel.setText(current.getPrivateAccount().getAccountName());
        clearingNumberLabel.setText(String.valueOf(current.getPrivateAccount().getClearingNbr()));
        clearingNumberLabel.setText(String.valueOf(current.getPrivateAccount().getClearingNbr()));
        accountNumberLabel.setText(String.valueOf(current.getPrivateAccount().getAccountNrb()));
        IBANNumberLabel.setText(String.valueOf(current.getPrivateAccount().getIBAN()));
        totalTransactionsLabel.setText(String.valueOf(current.getPrivateAccount().getTransactions().size()));

    }

    public void withdrawMoney(){
        clearTextArea();

        int  withdrawal = Integer.parseInt(withdrawField.getText());

        //check how much money we acctually have
        double saldo = AppConstants.getInstance().getLoggedInUser().getPrivateAccount().getSaldo();
        DecimalFormat df2 = new DecimalFormat("#.##");

       if(withdrawal> saldo){
           writeToTextArea("Error! You can't withdraw money! \nSaldo: "+df2.format(saldo)+ " kr" +"\nRequested withdrawal: " + withdrawal + " kr");
           return;
       }

        withdrawal *= -1;

        Transaction transaction = new Transaction("withdrawal", LocalDate.now(),withdrawal);
        AppConstants.getInstance().getLoggedInUser().getPrivateAccount().addTransaction(transaction);
        writeToTextArea("You successfully withdrew: " + withdrawField.getText() + " kr.");
    }

    public void depositMoney(){
        clearTextArea();
        Transaction transaction = new Transaction("Deposit",LocalDate.now(),Double.parseDouble(depositField.getText()));
        AppConstants.getInstance().getLoggedInUser().getPrivateAccount().addTransaction(transaction);
        writeToTextArea("You successfully deposited: " + depositField.getText() + " kr.");
    }

    public void getTransactions(){
        clearTextArea();
        writeToTextArea(AppConstants.getInstance().getLoggedInUser().getPrivateAccount().printTransactions());
    }

    public void getSaldo(){
        clearTextArea();
        DecimalFormat df2 = new DecimalFormat("#.##");
        double saldo = AppConstants.getInstance().getLoggedInUser().getPrivateAccount().getSaldo();
        writeToTextArea("Private account saldo: " + df2.format(saldo) + " kr");

    }

    public void getExpenses(){
        clearTextArea();
     ArrayList<Transaction> transactions = AppConstants.getInstance().getLoggedInUser().getPrivateAccount().getTransactions();
        DecimalFormat df2 = new DecimalFormat("#.##");
        double expenses = 0;
        for(Transaction transaction: transactions){
         //check if its negative
         if(transaction.getAmount() < 0){
             expenses+= transaction.getAmount();
         }
     }
        writeToTextArea("Your total expenses are: " +df2.format(expenses) + " kr");
    }

    public void createNewSavingsAccount(){
        clearTextArea();
        SavingsAccount savingsAccount = new SavingsAccount(accountName.getText(),Integer.parseInt(clearingNumber.getText()),Integer.parseInt(accountNumber.getText()),Integer.parseInt(IBANNumber.getText()),new ArrayList<>());
        AppConstants.getInstance().getLoggedInUser().getSavingsAccountList().add(savingsAccount);
        writeToTextArea("The savings account: " + accountName.getText() + " was successfully created!");
        //clearing fiels
        accountName.clear();
        clearingNumber.clear();
        accountNumber.clear();
        IBANNumber.clear();
    }

    public void getSavingsAccounts(){
        clearTextArea();
        ArrayList<SavingsAccount> temp = AppConstants.getInstance().getLoggedInUser().getSavingsAccountList();
        if(temp.size() == 0){
            writeToTextArea("You don't have any savings account yet, create one!");

        }else{
            for(SavingsAccount x: temp){
                writeToTextArea("Account name: " + x.getAccountName() + "\nClearing number: "+ x.getClearingNbr() +
                        "\nAccount number: "+ x.getAccountNrb()+"\nIBAN number: "+ x.getClearingNbr() + "\nTransactions: " + x.getTransactions().toString() +
                        "\n------------------------------------\n");

            }
        }


    }

    public void bindTextFieldsToCreateBtn(){
        createSavingsAccountBtn.disableProperty().bind(
                Bindings.isEmpty(accountName.textProperty())
                        .or(Bindings.isEmpty(clearingNumber.textProperty()))
                        .or(Bindings.isEmpty(accountNumber.textProperty()))
                        .or(Bindings.isEmpty(IBANNumber.textProperty())));
    }

    public void clearTextArea(){
        textArea.clear();
    }

    public void writeToTextArea(String text){
        textArea.appendText(text);
    }

}
