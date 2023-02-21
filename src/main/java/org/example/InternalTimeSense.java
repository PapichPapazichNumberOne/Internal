package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InternalTimeSense {
    public void internal() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to start the timer...");
        scanner.nextLine(); // wait for user to press Enter

        long startTime = System.currentTimeMillis();
        System.out.println("Press Enter again exactly one minute later.");

        try {
            scanner.nextLine(); // wait for user to press Enter again
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please press Enter.");
            scanner.nextLine();
        }

        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;
        long expectedTime = 60 * 1000; // 1 minute in milliseconds
        long error = Math.abs(expectedTime - elapsedTime);

        if (elapsedTime < expectedTime) {
            System.out.println("You pressed too early.");
        } else if (error > 1000) { // allow 1 second error margin
            System.out.println("You were " + error + " milliseconds too late.");
        } else {
            System.out.println("You have a great sense of time!");
        }
    }
}