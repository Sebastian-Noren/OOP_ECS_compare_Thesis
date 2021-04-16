package ecs_bank.ecs_core.components;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class PersonDetailComponent implements IComponent {
        public String firstName;
        public String lastName;
        public String ssn;

        public PersonDetailComponent(String firstName, String lastName, String ssn) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.ssn = ssn;
        }

        @Override
        public String toString() {
                return "PersonDetailComponent{" +
                        "firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", ssn='" + ssn + '\'' +
                        '}';
        }
}
