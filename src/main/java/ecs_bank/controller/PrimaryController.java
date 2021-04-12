package ecs_bank.controller;

import ecs_bank.AppConstants;
import ecs_bank.models.Admin;
import ecs_bank.models.Banker;
import ecs_bank.models.Customer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    @FXML
    public TextField textInputUsername,textInputPassword;
    @FXML
    public AnchorPane mainScene,sidePanel;
    @FXML
    public ChoiceBox registerChoiceBox;
    @FXML
    public Button registerBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppConstants.getInstance();
        System.out.println(AppConstants.getInstance().getCustomers().get(0).getPrivateAccount().getSaldo());
        //Initialize choiceBox (for register)
        choiceBoxInit();
        //disable register button
        registerBtn.setDisable(true);
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

        //========= validate user ========
        for(Customer customer: AppConstants.getInstance().getCustomers()){
            if (textInputUsername.getText().equals(customer.getSsn())){
                if(textInputPassword.getText().equals(customer.getPassWord())){
                    //current logged in user set
                    AppConstants.getInstance().setLoggedInUser(customer);
                    //validated
                   if( customer instanceof Banker){
                        //go to employee
                       loadScene("employee.fxml");
                    }else if (customer instanceof Admin){
                       //go to employee with more functionality or same as employee
                       loadScene("employee.fxml");
                   }else{
                       loadScene("customer.fxml");
                   }
                }
            }
        }
        clearTextFields();
    }

    public void register() throws IOException{
        String view = "";
        switch(registerChoiceBox.getValue().toString()){
            case "User":
                System.out.println("user");
                view = "registerUser.fxml";
                break;
            case "Employee":
                System.out.println("employee");
                view = "registerEmployee.fxml";
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }


        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/"+ view));
        mainScene.getChildren().setAll(pane);
    }


    private void loadScene(String fxml) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/"+fxml));
        mainScene.getChildren().setAll(pane);
    }


    private void clearTextFields(){
        textInputUsername.clear();
        textInputPassword.clear();
    }

    private boolean validateFields(){
        return !textInputPassword.getText().isEmpty() && !textInputUsername.getText().isEmpty();
    }

    private void choiceBoxInit(){
        registerChoiceBox.getItems().add("User");
        registerChoiceBox.getItems().add("Employee");
        //add listner

        registerChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                //when choice is done the register button will activate
                registerBtn.setDisable(false);
            }
        });
    }


}

