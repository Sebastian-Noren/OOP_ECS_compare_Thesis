package ecs_bank;

import ecs_bank.models.Address;
import ecs_bank.models.Customer;
import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.Transaction;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AppConstants {


    public static AppConstants instance = null;
    private ArrayList<Customer> customers;



    public static AppConstants getInstance() {
        if (instance == null) {
            instance = new AppConstants();
        }
        return instance;
    }

    public AppConstants() {
        customers = new ArrayList<>();
        initTest();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    private void initTest(){
        Transaction transaction = new Transaction("First salary",new Date(),1000);
        System.out.println(transaction);
        PrivateAccount privateAccount = new PrivateAccount("My Account", 53234,34254234,2323, new ArrayList<>());
        privateAccount.deposit(transaction);
        System.out.println(privateAccount.getSaldo());
        privateAccount.withdraw(new Transaction("Buying toys",new Date(),-437.59));
        System.out.println(privateAccount.getSaldo());


        Address address = new Address("Korsvagen",5,"Mamlut","Swedan");

        Customer customer = new Customer("Sebastian", "Muhammed", "1993xxxx-xxxx", address,new Date(),
                "0736-5554", privateAccount);

        customers.add(customer);

      //  DatabaseConnection.getInstance().connect();
      //  System.out.println(DatabaseConnection.getInstance().getHashPassword("sebastian"));
    }
}
