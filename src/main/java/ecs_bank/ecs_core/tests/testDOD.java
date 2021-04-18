package ecs_bank.ecs_core.tests;



import ecs_bank.ecs_core.components.AddressComponent;

import java.util.Vector;


public class testDOD {

    public static void main(String[] args) {
        // write your code here
        int k = 1_0000_0000;
        AddressComponent[] arr = new AddressComponent[k];

        Vector<AddressComponent> lol = new Vector<>();

        int[] warmup = new int[1000];

        for (int i = 0; i < warmup.length; i++) {
            warmup[i] = i;
        }

       // AddressComponent add = new AddressComponent();
      //  add.address = "fältvägen";


        long startTime = System.nanoTime();
        for (int i = 0; i < k; i++) {
            arr[i] = new AddressComponent("gatan",1,43545,"Lund","Swe");

        }

        long endTime = System.nanoTime();

        // get difference of two nanoTime values
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in nanoseconds  : " + timeElapsed);
        System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);

        startTime = System.nanoTime();
        for (int i = 0; i < k; i++) {
            arr[i].street = "";
        }

        endTime = System.nanoTime();

        // get difference of two nanoTime values
        timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds  : " + timeElapsed);
        System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);

        System.out.println("ARRAYLIST");

        startTime = System.nanoTime();
        for (int i = 0; i < k; i++) {
            lol.add(new AddressComponent("gatan",1,43545,"Lund","Swe"));
        }

        endTime = System.nanoTime();

        // get difference of two nanoTime values
        timeElapsed = endTime - startTime;

        System.out.println("Execution time in nanoseconds  : " + timeElapsed);
        System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);

        startTime = System.nanoTime();
        for (int i = 0; i < k; i++) {
            lol.get(i).street = "";
        }

        endTime = System.nanoTime();

        // get difference of two nanoTime values
        timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds  : " + timeElapsed);
        System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);

    }
}
