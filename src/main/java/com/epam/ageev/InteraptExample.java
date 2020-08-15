package com.epam.ageev;

import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Matcher;

public class InteraptExample {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(()->{
            Random random =new Random();
            for (int i = 0;i<1_000_000_000;i++){
               if(Thread.currentThread().isInterrupted()){
                   System.out.println("tred was interapted");
                   break;
               }
                Math.sin(random.nextDouble());
                System.out.println(i);
            }
        });

        System.out.println("Starting....");
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
        System.out.println("Finished");
    }
}
