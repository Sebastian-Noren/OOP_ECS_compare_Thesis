package ecs_bank.ecs_core.tests;

import ecs_bank.AppConstants;
import ecs_bank.ecs_core.components.PersonDetailComponent;
import ecs_bank.ecs_core.components.PrivateAccountComponent;
import ecs_bank.ecs_core.components.SavingsAccountComponent;
import ecs_bank.ecs_core.components.TestDataComponent;
import ecs_bank.ecs_core.systems.TestDataSystem;
import ecs_bank.ecs_core.systems.TestFindSSNSystem;
import ecs_bank.ecs_core.systems.TestGetSaldoSystem;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class TestEnvironmentRefernce {
    public static void main(String[] args) {

        AppConstants.getInstance();

        final int TEST_LOOPS = 10;


        // ***************************** TEST CASE 1 **************************************************
        System.out.println("\n\t***************************** TEST CASE 1 **************************************************");
        int a = 100;
        int b = 63;

        TestDataSystem testDataSystem = new TestDataSystem();
        System.out.println("\n\t******* ECS *******");
        float averageTime = 0;
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            long startTime = System.nanoTime();

            testDataSystem.process(AppConstants.getInstance().getManagerECS().getAllComponentsOfType(TestDataComponent.class), a, b);

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //     System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);


        System.out.println("\n\t******* OOP *******");
        averageTime = 0;
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            long startTime = System.nanoTime();

            for (int i = 0; i < AppConstants.getInstance().getTestObjectsList().size(); i++) {
                AppConstants.getInstance().getTestObjectsList().get(i).testLeafNodeOOP(a, b);
                b++;
            }

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //    System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);


        // ***************************** TEST CASE 2 **************************************************
        System.out.println("\n\t***************************** TEST CASE 2 **************************************************");
        String SSN = "19891110-3532";

        TestFindSSNSystem testFindSSNSystem = new TestFindSSNSystem();

        System.out.println("\n\t******* ECS *******");
        averageTime = 0;
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            long startTime = System.nanoTime();

            testFindSSNSystem.process(AppConstants.getInstance().getManagerECS().getAllComponentsOfType(PersonDetailComponent.class), SSN);

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //   System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);


        System.out.println("\n\t******* OOP *******");

        averageTime = 0;
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            boolean ssnFound = false;
            long startTime = System.nanoTime();

            for (int i = 0; i < AppConstants.getInstance().getTestObjectsList().size(); i++) {
                if (AppConstants.getInstance().getTestObjectsList().get(i).getSsn().equals(SSN) && !AppConstants.getInstance().getTestObjectsList().get(i).getSsn().isEmpty()) {
                    ssnFound = true;
                    break;

                } else {
                    int x = 5 + 3 * 2;
                }
            }

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //    System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);


        // ***************************** TEST CASE 3 **************************************************
        System.out.println("\n\t***************************** TEST CASE 3 **************************************************");

        System.out.println("\n\t******* ECS *******");

        averageTime = 0;
        TestGetSaldoSystem testCountBankSaldoSystem = new TestGetSaldoSystem();
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            long startTime = System.nanoTime();

            testCountBankSaldoSystem.processPrivateAccunt(AppConstants.getInstance().getManagerECS().getAllComponentsOfType(PrivateAccountComponent.class));
            testCountBankSaldoSystem.processSavingsAccount(AppConstants.getInstance().getManagerECS().getAllComponentsOfType(SavingsAccountComponent.class));

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //    System.out.println("Execution time in milliseconds : " + ms);
        }
        System.out.println("average milliseconds : " + averageTime / TEST_LOOPS);

        System.out.println("\n\t******* OOP *******");

        averageTime = 0;
        System.out.println("Test Starts");
        for (int k = 0; k < TEST_LOOPS; k++) {

            runWarmUp();

            long startTime = System.nanoTime();

            for (int i = 0; i < AppConstants.getInstance().getTestObjectsList().size(); i++) {
                AppConstants.getInstance().getTestObjectsList().get(i).getPrivateAccount().getSaldo();
                AppConstants.getInstance().getTestObjectsList().get(i).getSavingsAccountList().get(0).getSaldo();
            }

            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            float ms = (float) timeElapsed / 1000000;
            averageTime += ms;
            //   System.out.println("Execution time in milliseconds : " + ms);
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
