package ecs_bank.controller;

import ecs_bank.App;
import ecs_bank.AppConstants;
import ecs_bank.DatabaseConnection;
import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    @FXML
    public TextField textInputUsername,textInputPassword;
    @FXML
    public AnchorPane mainScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AppConstants.getInstance();
        System.out.println(AppConstants.getInstance().getCustomers().get(0).getPrivateAccount().getSaldo());
    }


    public void login() throws IOException {
        /*   TODO
            - Felmeddelanden
            - validera användare genom databas
            - om vanlig användare skicka customer.fxml (inte skappad)
            - om employee skicka employee.fxml
         */
        if(!validateFields()){
            System.out.println("One or both fields are not filled!");
            return;
        }
        clearTextFields();
        //validate user via database

        //success
        changeScene("employee.fxml");
    }

    private void changeScene(String fxml) throws IOException {
        AnchorPane pane = FXMLLoader.load(App.class.getResource(fxml));
        mainScene.getChildren().setAll(pane);
    }


    private void clearTextFields(){
        textInputUsername.clear();
        textInputPassword.clear();
    }

    private boolean validateFields(){
        return !textInputPassword.getText().isEmpty() && !textInputUsername.getText().isEmpty();
    }
}

