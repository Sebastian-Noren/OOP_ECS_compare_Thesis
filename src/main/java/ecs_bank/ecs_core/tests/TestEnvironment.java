package ecs_bank.ecs_core.tests;

import ecs_bank.AppConstants;
import ecs_bank.ecs_core.components.PersonDetailComponent;
import ecs_bank.ecs_core.components.PrivateAccountComponent;
import ecs_bank.ecs_core.components.SavingsAccountComponent;
import ecs_bank.ecs_core.components.TestDataComponent;
import ecs_bank.ecs_core.systems.TestGetSaldoSystem;
import ecs_bank.ecs_core.systems.TestDataSystem;
import ecs_bank.ecs_core.systems.TestFindSSNSystem;

import java.util.ArrayList;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TestEnvironment {
    public static void main(String[] args) {

        AppConstants.getInstance();

        final int TEST_LOOPS = 100;


        // ***************************** TEST CASE 1 **************************************************
        System.out.println("\n\t***************************** TEST CASE 1 **************************************************");

        System.out.println("\n\t******* GET Data *******");
        float averageTime = 0;
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            long startTime = System.nanoTime();

            AppConstants.getInstance().getManagerECS().getAllComponentsOfType(TestDataComponent.class);

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //     System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);



        int a = 100;
        int b = 63;

        TestDataSystem testDataSystem = new TestDataSystem();
        System.out.println("\n\t******* ECS *******");
        averageTime = 0;
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            ArrayList<TestDataComponent> list = AppConstants.getInstance().getManagerECS().getAllComponentsOfType(TestDataComponent.class);
            long startTime = System.nanoTime();

            testDataSystem.process(list, a, b);

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //     System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);


        // ***************************** TEST CASE 2 **************************************************
        System.out.println("\n\t***************************** TEST CASE 2 **************************************************");

        System.out.println("\n\t******* GET Data *******");
        averageTime = 0;
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            long startTime = System.nanoTime();

            AppConstants.getInstance().getManagerECS().getAllComponentsOfType(PersonDetailComponent.class);

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //     System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);


        String SSN = "19891110-3532";

        TestFindSSNSystem testFindSSNSystem = new TestFindSSNSystem();

        System.out.println("\n\t******* ECS *******");
        averageTime = 0;
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            ArrayList<PersonDetailComponent> list = AppConstants.getInstance().getManagerECS().getAllComponentsOfType(PersonDetailComponent.class);
            long startTime = System.nanoTime();

            testFindSSNSystem.process(list, SSN);

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //   System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);


        // ***************************** TEST CASE 3 **************************************************
        System.out.println("\n\t***************************** TEST CASE 3 **************************************************");

        System.out.println("\n\t******* GET Data *******");
        averageTime = 0;
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            long startTime = System.nanoTime();

            AppConstants.getInstance().getManagerECS().getAllComponentsOfType(PrivateAccountComponent.class);
            AppConstants.getInstance().getManagerECS().getAllComponentsOfType(SavingsAccountComponent.class);

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //     System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);


        System.out.println("\n\t******* ECS *******");

        averageTime = 0;
        TestGetSaldoSystem testCountBankSaldoSystem = new TestGetSaldoSystem();
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            ArrayList<PrivateAccountComponent> list = AppConstants.getInstance().getManagerECS().getAllComponentsOfType(PrivateAccountComponent.class);
            ArrayList<SavingsAccountComponent> list2 = AppConstants.getInstance().getManagerECS().getAllComponentsOfType(SavingsAccountComponent.class);
            long startTime = System.nanoTime();

            testCountBankSaldoSystem.processPrivateAccunt(list);
            testCountBankSaldoSystem.processSavingsAccount(list2);

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //    System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);


    }

    private static void runWarmUp() {
        int[] warmup = new int[1000];
        for (int i = 0; i < warmup.length; i++) {
            warmup[i] = i;
        }
    }
}
