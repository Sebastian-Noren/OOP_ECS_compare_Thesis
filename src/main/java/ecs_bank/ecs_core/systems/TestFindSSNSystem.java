package ecs_bank.ecs_core.systems;

import ecs_bank.ecs_core.components.PersonDetailComponent;
import ecs_bank.ecs_core.components.TestDataComponent;

import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TestFindSSNSystem implements ISystemECS {

    public void process(ArrayList<PersonDetailComponent> list, String SSN) {
        boolean ssnFound = false;
        for (PersonDetailComponent testDataComponent : list) {
            if (testDataComponent.ssn.equals(SSN) && !testDataComponent.ssn.isEmpty()) {
                ssnFound = true;
                break;
            } else {
                int x = 5 + 3 * 2;
            }

        }
    }
}
