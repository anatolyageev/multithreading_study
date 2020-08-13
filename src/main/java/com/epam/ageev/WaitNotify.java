package com.epam.ageev;

import java.util.Scanner;

public class WaitNotify {
    public static void main(String[] args) throws InterruptedException {
        WWaitAndNotify wn = new WWaitAndNotify();

        Thread thread1 = new Thread(()->
        {
            try {
                wn.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(()->{
            try {
                wn.concume();
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

class WWaitAndNotify{

    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Produser thred started");
            wait();//1 - отдаем монитор (intrinsic lock) 2 - ждем пока будет вызван notify()
                    // вызов привязывается к тому контексту в котром сейчас находимся поэтому нужно четко указывать
            // объект на котором происходит синхронизация и затем .wait();
            System.out.println("Produser thred resumed...");
        }

    }

    public void concume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (this){
            System.out.println("Waiting for return key");
            scanner.nextLine();
            notify();
            Thread.sleep(5000);
        }
    }
}