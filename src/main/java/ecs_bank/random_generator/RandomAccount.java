package ecs_bank.random_generator;

import java.security.SecureRandom;

public class RandomAccount {

    private SecureRandom random = new SecureRandom();
    private String[] accNames = {"My Private Account", "Debit Account", "Private Account", "Salary Account"};
    private String[] saveAccNames = {"Savings Account", "Family Account", "Buffer Account", "Vacation Account","Pension Account"};



    public String getAccountName(boolean savingsAccount) {
        if (savingsAccount) { // 20%
            return saveAccNames[random.nextInt(saveAccNames.length)];
        }
        return accNames[random.nextInt(accNames.length)];
    }

    public int getClearingNbr() {
        return random.nextInt(89999) + 10000;
    }

    public int getAccountNrb() {
        return random.nextInt(8999999) + 1000000;
    }

    public int getIBAN() {
        return random.nextInt(8999) + 1000;
    }


}
