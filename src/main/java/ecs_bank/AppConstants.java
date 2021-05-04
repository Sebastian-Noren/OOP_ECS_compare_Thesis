package ecs_bank;

import ecs_bank.ecs_core.EntityManagerECS;
import ecs_bank.ecs_core.components.*;
import ecs_bank.models.Address;
import ecs_bank.models.Banker;
import ecs_bank.models.Customer;
import ecs_bank.models.TestDataOOP;
import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.SavingsAccount;
import ecs_bank.models.accounts.Transaction;
import ecs_bank.random_generator.*;
import ecs_bank.random_generator.address.RandomAddress;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static ecs_bank.random_generator.RandomDate.getRandomDate;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AppConstants {

    //1 000 000 K safe
    public static final int N = 500_000;

    public static AppConstants instance = null;
    private ArrayList<Customer> customers;
    private HashMap<String, Customer> customerMap;
    private Customer loggedInUser;

    private ArrayList<TestDataOOP> testObjectsList;


    // ECS
    private EntityManagerECS managerECS;
    private HashMap<String, Integer> entityMapECS;


    public static AppConstants getInstance() {
        if (instance == null) {
            instance = new AppConstants();
        }
        return instance;
    }

    public AppConstants() {
        customers = new ArrayList<>();
        customerMap = new HashMap<>();
        managerECS = new EntityManagerECS(N);
        entityMapECS = new HashMap<>();
        testObjectsList = new ArrayList<>(N);
        initTest();
    }

    public ArrayList<TestDataOOP> getTestObjectsList() {
        return testObjectsList;
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

    public EntityManagerECS getManagerECS() {
        return managerECS;
    }


    private void initTest() {

        Transaction transaction = new Transaction("First salary", LocalDate.now(), 1000);
        PrivateAccount privateAccount = new PrivateAccount("My Account", 53234, 34254234, 2323, new ArrayList<>());
        privateAccount.addTransaction(transaction);
        privateAccount.addTransaction(new Transaction("Buying toys", LocalDate.now(), -437.59));


        Address address = new Address("Korsvagen", 5, 24343, "Mamlut", "Swedan");

        Customer customer = new Customer("Sebastian", "Muhammed", "1", address, LocalDate.now(),
                "0736-5554", "1", privateAccount);


        Banker banker = new Banker("ali", "yes", "190303",
                new Address("gatan", 1, 43545, "Lund", "Swe"), LocalDate.now(), "22", "1234",
                new PrivateAccount("hej", 23, 3, 3, new ArrayList<>()));


        customers.add(banker);
        customers.add(customer);


        customerMap.put(customer.getSsn(), customer);
        customerMap.put(banker.getSsn(), banker);


        System.out.println("GENERATING DATA");
        long startTime = System.nanoTime();

        // **** GENERATE DATA *********

        // init randomClasses
        FirstNameSource firstNameSource = new FirstNameSource();
        SurnameSource surnameSource = new SurnameSource();
        RandomAddress randomAddress = new RandomAddress();
        RandomAccount randomAccount = new RandomAccount();
        RandomTransaction randomTransaction = new RandomTransaction();

        /*
        testObjectsList.add(new TestDataOOP("Sebastian", "Norén", "19891110-3532", address, LocalDate.now(),
                "0736-229145", "1234", privateAccount));

         */


        // generate random persons
        Random r = new Random();
        for (int i = 0; i < N; i++) {

            PersonalNumber personalNumber = createRandomPersonalNumber();

            String firstName = firstNameSource.random(personalNumber.getSerialNumber());
            String surname = surnameSource.random();
            String ssn = personalNumber.getPersonalNumber();

            LocalDate date = getRandomDate(personalNumber.getBirthYear());

            int regDate = date.getYear();


            ArrayList<Transaction> transactions = new ArrayList<>();
            ArrayList<TransactionComponent> transactionComponents = new ArrayList<>();

            transactions.add(new Transaction(
                    "Start Amount",
                    LocalDate.of(regDate, Month.JANUARY, 1),
                    15_000));

            transactionComponents.add(new TransactionComponent(
                    "Start Amount",
                    LocalDate.of(regDate, Month.JANUARY, 1),
                    15_000));

            int transactionCount = 39;
            for (int j = 0; j < transactionCount; j++) {
                int chance = r.nextInt(100);

                String desc = randomTransaction.getDescription(chance);
                LocalDate localDate = randomTransaction.getTransactionDate(regDate);
                double amount = randomTransaction.getAmount(chance);

                transactions.add(new Transaction(
                        desc,
                        localDate,
                        amount));

                transactionComponents.add(new TransactionComponent(
                        desc,
                        localDate,
                        amount));
            }

            String privAccName = randomAccount.getAccountName(false);
            int privClearNbr = randomAccount.getClearingNbr();
            int privAccNbr = randomAccount.getAccountNrb();
            int privIban = randomAccount.getIBAN();

            String saveAccName = randomAccount.getAccountName(true);
            int saveClearNbr = randomAccount.getClearingNbr();
            int saveAccNbr = randomAccount.getAccountNrb();
            int saveIban = randomAccount.getIBAN();


            String street = randomAddress.getStreet();
            int streetNumber = randomAddress.getStreetNumber();
            int zipCode = randomAddress.getZipCode();
            String city = randomAddress.getCity();
            String country = randomAddress.getCountry();

            String phoneNumber = createPhoneNumber();

            TestDataOOP randomPerson = new TestDataOOP(firstName, surname, ssn,
                    new Address(
                            street,
                            streetNumber,
                            zipCode,
                            city,
                            country
                    ), date, phoneNumber, "1234",
                    new PrivateAccount(
                            privAccName,
                            privClearNbr,
                            privAccNbr,
                            privIban,
                            transactions));

            randomPerson.addSavingsAccountToList(new SavingsAccount(
                    saveAccName,
                    saveClearNbr,
                    saveAccNbr,
                    saveIban,
                    transactions));

            testObjectsList.add(randomPerson);

            randomPerson.getPrivateAccount().getSaldo();


            // CREATE Entities
            int entity = managerECS.createEntity();

            // Create customer 0
            managerECS.addComponent(entity, new PersonDetailComponent(firstName, surname, ssn));
            managerECS.addComponent(entity, new AddressComponent(street, streetNumber, zipCode, city, country));
            managerECS.addComponent(entity, new BankMemberDetailsComponent(phoneNumber, "1234", date));
            managerECS.addComponent(entity, new PrivateAccountComponent(privAccName, privClearNbr, privAccNbr, privIban, transactionComponents));
            managerECS.addComponent(entity, new SavingsAccountComponent(saveAccName, saveClearNbr, saveAccNbr, saveIban, transactionComponents));
            managerECS.addComponent(entity, new TestDataComponent());

        }

/*
        // CREATE Entities
        int zero = managerECS.createEntity();

        // Create customer 0
        managerECS.addComponent(zero, new PersonDetailComponent("Sebastian", "Norlen", "198xxxxx-xxxx"));
        managerECS.addComponent(zero, new AddressComponent("Fältvägen", 1, 29369, "Krisitanstad", "Sweden"));
        managerECS.addComponent(zero, new BankMemberDetailsComponent("0736-229145", "1", LocalDate.now()));
        managerECS.addComponent(zero, new PrivateAccountComponent("Private Account", 53526, 354545154, 35454, new ArrayList<>()));
        managerECS.addComponent(zero, new TestDataComponent());

        entityMapECS.put("198xxxxx-xxxx", zero);


        //       System.out.println(entityMapECS.get("198xxxxx-xxxx"));

        int entity = entityMapECS.get("198xxxxx-xxxx");

        //    System.out.println(managerECS.getComponent(entity,PersonDetailComponent.class).toString());
*/

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("GENERATING DATA - COMPLETE!!!!");
        System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);

    }


    private static PersonalNumber createRandomPersonalNumber() {
        RandomPersonalNumber ssn = new RandomPersonalNumber();
        return new PersonalNumber(
                ssn.getYear(),
                ssn.getMonth(),
                ssn.getDay(),
                ssn.getSerialNumber(),
                ssn.getControlNumber());
    }

    private String createPhoneNumber() {
        SecureRandom random = new SecureRandom();

        int num1 = random.nextInt(9);
        int group1 = random.nextInt(899) + 100;
        int group2 = random.nextInt(8999) + 1000;
        return String.format("07%d-%d%d", num1, group1, group2);

    }
}
