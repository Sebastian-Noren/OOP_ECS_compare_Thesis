package ecs_bank;

import ecs_bank.models.Address;
import ecs_bank.models.Banker;
import ecs_bank.models.Customer;
import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AppConstants {


    public static AppConstants instance = null;
    private ArrayList<Customer> customers;
    private HashMap<String,Customer> customerMap;



    public static AppConstants getInstance() {
        if (instance == null) {
            instance = new AppConstants();
        }
        return instance;
    }

    public AppConstants() {
        customers = new ArrayList<>();
        customerMap = new HashMap<>();
        initTest();
    }

    public HashMap<String, Customer> getCustomerMap() {
        return customerMap;
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


        Address address = new Address("Korsvagen",5,24343,"Mamlut","Swedan");

        Customer customer = new Customer("Sebastian", "Muhammed", "1993xxxx-xxxx", address,new Date(),
                "0736-5554", "1234", privateAccount);

        customerMap.put(customer.getSsn(),customer);

        Banker banker = new Banker("ali","yes","190303",
                new Address("gatan",1,43545,"Lund","Swe"),new Date() ,"22", "1234",
                new PrivateAccount("hej",23,3,3,new ArrayList<>()));

        customers.add(banker);
        customers.add(customer);

        customerMap.put(customer.getSsn(),customer);
        customerMap.put(banker.getSsn(),banker);

      //  DatabaseConnection.getInstance().connect();
      //  System.out.println(DatabaseConnection.getInstance().getHashPassword("sebastian"));
    }
}
