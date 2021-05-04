package ecs_bank.ecs_core.components;

import java.time.LocalDate;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class BankMemberDetailsComponent implements IComponent {
    public String phoneNumber;
    public String passWord;
    public LocalDate registrationDate;

    public BankMemberDetailsComponent(String phoneNumber, String passWord, LocalDate registrationDate) {
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.registrationDate = registrationDate;
    }
}
