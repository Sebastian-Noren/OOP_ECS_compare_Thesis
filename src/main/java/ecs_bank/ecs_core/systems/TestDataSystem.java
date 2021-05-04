package ecs_bank.ecs_core.systems;

import ecs_bank.ecs_core.components.PrivateAccountComponent;
import ecs_bank.ecs_core.components.TestDataComponent;

import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TestDataSystem implements ISystemECS{

    public void process(ArrayList<TestDataComponent> list, int a, int b) {

        for (TestDataComponent testDataComponent : list) {
            testDataComponent.A = a;
            testDataComponent.B = (b % 6) + 1;
            int result = testDataComponent.A + testDataComponent.B;
            if (result > 10) {
                testDataComponent.C = result;
            } else {
                testDataComponent.C = 100;
            }
            b++;
        }

    }

}
