package com.epam.ageev;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ProducerConsumerWaitNotify {

    public static void main(String[] args) throws InterruptedException {
        ProducerConcumer pc = new ProducerConcumer();

        Thread thread1 = new Thread(() ->
        {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

class ProducerConcumer {
    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true){
            synchronized (lock){
                while (queue.size()==LIMIT){
                    lock.wait();
           //         lock.notify();
                }
                queue.offer(value++);
                lock.notify();
            }
        }
    }


    public void consume() throws InterruptedException {
        while (true){
            synchronized (lock){
                while (queue.size()==0){
                    lock.wait();
                }
               int valu = queue.poll();
                System.out.println(valu);
                System.out.println("Que size: " + queue.size());
                lock   .notify();
            }
            Thread.sleep(1000);
        }
    }
}
