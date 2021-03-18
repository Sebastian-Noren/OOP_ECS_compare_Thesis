package ecs_bank;

import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.Transaction;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Transaction transaction = new Transaction("First salary",new Date(),1000);

        System.out.println(transaction);


        PrivateAccount privateAccount = new PrivateAccount("My Account", 53234,34254234,2323, new ArrayList<>());

        privateAccount.deposit(transaction);

        System.out.println(privateAccount.getSaldo());

        privateAccount.withdraw(new Transaction("Buying toys",new Date(),-437.59));

        System.out.println(privateAccount.getSaldo());
    }
}
