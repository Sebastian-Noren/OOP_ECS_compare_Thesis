package ecs_bank.random_generator;

import ecs_bank.models.Address;
import ecs_bank.models.Customer;
import ecs_bank.models.accounts.PrivateAccount;
import ecs_bank.models.accounts.SavingsAccount;
import ecs_bank.models.accounts.Transaction;
import ecs_bank.random_generator.address.RandomAddress;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Random;

import static ecs_bank.random_generator.RandomDate.getRandomDate;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class Generator {
    public static void main(String[] args) {

        FirstNameSource firstNameSource = new FirstNameSource();
        SurnameSource surnameSource = new SurnameSource();
        RandomAccount randomAccount = new RandomAccount();
        RandomTransaction  randomTransaction = new RandomTransaction();

        PersonalNumber personalNumber = createRandomPersonalNumber();


        String firstName = firstNameSource.random(personalNumber.getSerialNumber());
        String surname = surnameSource.random();
        String ssn = personalNumber.getPersonalNumber();

        LocalDate date = getRandomDate(personalNumber.getBirthYear());

        int regDate = date.getYear();

        PrivateAccount privateAccount = new PrivateAccount(
                randomAccount.getAccountName(false),
                randomAccount.getClearingNbr(),
                randomAccount.getAccountNrb(),
                randomAccount.getIBAN(),
                new ArrayList<>());

        privateAccount.addTransaction(new Transaction(
                "Start Amount",
                LocalDate.of(regDate, Month.JANUARY, 1),
                15_000));

        Random r = new Random();
        int transactionCount = r.nextInt(100);
        for (int i = 0; i < transactionCount ; i++) {
            int chance = r.nextInt(100);
            privateAccount.addTransaction(new Transaction(
                    randomTransaction.getDescription(chance),
                    randomTransaction.getTransactionDate(regDate),
                    randomTransaction.getAmount(chance)));
        }

        SavingsAccount savingsAccount = new SavingsAccount(
                randomAccount.getAccountName(true),
                randomAccount.getClearingNbr(),
                randomAccount.getAccountNrb(),
                randomAccount.getIBAN(),
                new ArrayList<>());


        savingsAccount.addTransaction(new Transaction(
                "Start Amount",
                LocalDate.of(regDate, Month.JANUARY, 1),
                15_000));

        transactionCount = r.nextInt(100);
        for (int i = 0; i < transactionCount ; i++) {
            int chance = r.nextInt(100);
            savingsAccount.addTransaction(new Transaction(
                    randomTransaction.getDescription(chance),
                    randomTransaction.getTransactionDate(regDate),
                    randomTransaction.getAmount(chance)));
        }

        Customer customer = new Customer(firstName, surname, ssn, createRandomAddress(), date,
                createPhoneNumber(), "1", privateAccount);

        customer.addSavingsAccountToList(savingsAccount);


        System.out.println(
                "Name: " + customer.getFirstName() + " " + customer.getLastName() +
                " SSN: " + customer.getSsn() +
                " Address: " + customer.getAddress());

        System.out.println(customer.toString() +" " + customer.getPrivateAccount().getSaldo());


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

    private static Address createRandomAddress() {
        RandomAddress address = new RandomAddress();
        return new Address(
                address.getStreet(),
                address.getStreetNumber(),
                address.getZipCode(),
                address.getCity(),
                address.getCountry()
        );
    }

    private static String createPhoneNumber() {
        SecureRandom random = new SecureRandom();

        int num1 = random.nextInt(9);
        int group1 = random.nextInt(899) + 100;
        int group2 = random.nextInt(8999) + 1000;
        return String.format("07%d-%d%d", num1, group1, group2);

    }


}
