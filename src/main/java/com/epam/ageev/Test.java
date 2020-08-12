package com.epam.ageev;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;

public class Test {
    private  int counter;

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.doWork();
        //   System.out.println(test.counter);
        System.out.println("From main " + test.counter);
    }

    public synchronized void  increment(){
        counter++;
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();// присоединяем к майн, майн остановится
        thread2.join();
       System.out.println("From method " + counter);
    }

}





