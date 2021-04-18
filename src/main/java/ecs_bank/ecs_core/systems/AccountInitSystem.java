package ecs_bank.ecs_core.systems;

import ecs_bank.ecs_core.components.PrivateAccountComponent;

import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AccountInitSystem implements ISystemECS {

    public void process(ArrayList<PrivateAccountComponent> list) {


        long startTime = System.nanoTime();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).clearingNbr);
        }

        long endTime = System.nanoTime();

        // get difference of two nanoTime values
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in nanoseconds  : " + timeElapsed);
        System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);

    }
}
