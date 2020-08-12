package com.epam.ageev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SycronyzeTest {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.main();
    }
}

    class Worker{
        private Random random = new Random();
        private Object lock1 = new Object();
        private Object lock2 = new Object();
        private List<Integer> integers1 = new ArrayList<>();
        private List<Integer> integers2 = new ArrayList<>();

        public void addToList1() throws InterruptedException {
            synchronized (lock1) {
                for (int i = 0; i < 1000; i++) {
                    Thread.sleep(1);
                    integers1.add(random.nextInt(100));
                }
            }
        }

        public void addToList2() throws InterruptedException {
            synchronized (lock2) {
                for (int i = 0; i < 1000; i++) {
                    Thread.sleep(1);
                    integers2.add(random.nextInt(100));
                }
            }
        }

        public void work() throws InterruptedException {
            addToList1();
            addToList2();
        }

        public void main() throws InterruptedException {
            long befor = System.currentTimeMillis();

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();


            long after = System.currentTimeMillis();
            System.out.println("Teks " + (after- befor));

            System.out.println("List1 " + integers1.size());
            System.out.println("List2 " + integers2.size());
        }
    }

