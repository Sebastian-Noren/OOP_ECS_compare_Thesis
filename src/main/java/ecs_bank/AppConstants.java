package ecs_bank;

import ecs_bank.ecs_core.EntityManagerECS;
import ecs_bank.ecs_core.components.AddressComponent;
import ecs_bank.ecs_core.components.MemberDetailsComponent;
import ecs_bank.ecs_core.components.PersonDetailComponent;
import ecs_bank.ecs_core.components.PrivateAccountComponent;
import ecs_bank.models.Address;
import ecs_bank.models.Banker;
import ecs_bank.models.Customer;
import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AppConstants {


    public static AppConstants instance = null;
    private ArrayList<Customer> customers;
    private HashMap<String,Customer> customerMap;
    private Customer loggedInUser;


    private HashMap<String,Integer> entityMapECS;


    public static AppConstants getInstance() {
        if (instance == null) {
            instance = new AppConstants();
        }
        return instance;
    }

    public AppConstants() {
        customers = new ArrayList<>();
        customerMap = new HashMap<>();
        entityMapECS = new HashMap<>();
        initTest();
    }

    public Customer getLoggedInUser() {
        return loggedInUser;
    }
    public void setLoggedInUser(Customer loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    public HashMap<String, Customer> getCustomerMap() {
        return customerMap;
    }
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    private void initTest(){

        Transaction transaction = new Transaction("First salary",LocalDate.now(),1000);
        PrivateAccount privateAccount = new PrivateAccount("My Account", 53234,34254234,2323, new ArrayList<>());
        privateAccount.addTransaction(transaction);
        privateAccount.addTransaction(new Transaction("Buying toys", LocalDate.now(),-437.59));



        Address address = new Address("Korsvagen",5,24343,"Mamlut","Swedan");

        Customer customer = new Customer("Sebastian", "Muhammed", "1", address,LocalDate.now(),
                "0736-5554", "1", privateAccount);


        Banker banker = new Banker("ali","yes","190303",
                new Address("gatan",1,43545,"Lund","Swe"),LocalDate.now() ,"22", "1234",
                new PrivateAccount("hej",23,3,3,new ArrayList<>()));

        customers.add(banker);
        customers.add(customer);

        customerMap.put(customer.getSsn(),customer);
        customerMap.put(banker.getSsn(),banker);



        // ECS
        EntityManagerECS managerECS = new EntityManagerECS();

        // CREATE Entities
        int zero = managerECS.createEntity();

        // Create customer 0
        managerECS.addComponent(zero, new PersonDetailComponent("Sebastian", "Norlen", "198xxxxx-xxxx"));
        managerECS.addComponent(zero, new AddressComponent("Fältvägen",1,29369,"Krisitanstad","Sweden"));
        managerECS.addComponent(zero, new MemberDetailsComponent("0736-229145","1",LocalDate.now()));
        managerECS.addComponent(zero, new PrivateAccountComponent("Private Account",53526,354545154,35454,new ArrayList<>()));

        entityMapECS.put("198xxxxx-xxxx",zero);


        System.out.println(entityMapECS.get("198xxxxx-xxxx"));

        int entity = entityMapECS.get("198xxxxx-xxxx");

        System.out.println(managerECS.getComponent(entity,PersonDetailComponent.class).toString());

    }
}
