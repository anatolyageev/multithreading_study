package com.epam.ageev;

import java.util.Random;
import java.util.concurrent.*;

public class CallableFutureExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("Start");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish");

            Random random = new Random();

            return random.nextInt(100);
        });

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(future.get());// get - дожидается окончания потока
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static int calculate() {
        return 5 + 4;
    }
}
