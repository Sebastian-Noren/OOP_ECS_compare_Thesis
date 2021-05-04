package ecs_bank.models;

import ecs_bank.models.Address;
import ecs_bank.models.Banker;
import ecs_bank.models.accounts.PrivateAccount;

import java.time.LocalDate;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TestDataOOP extends Banker {

    private int A;
    private int B;
    private int C;

    public TestDataOOP(String firstName, String lastName, String ssn, Address address, LocalDate registrationDate, String phoneNumber, String password, PrivateAccount privateAccount) {
        super(firstName, lastName, ssn, address, registrationDate, phoneNumber, password, privateAccount);

        this.A = 0;
        this.B = 0;
        this.C = 0;
    }


    public void testLeafNodeOOP(int a, int b) {
        this.A = a;
        this.B = (b % 6) + 1;;

        int result = this.A + this.B;
        if (result > 10) {
            this.C = result;
        } else {
            this.C = 100;
        }
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    public int getC() {
        return C;
    }
}
