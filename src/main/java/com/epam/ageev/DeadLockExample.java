package com.epam.ageev;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread thread1 = new Thread(() -> {
            runner.firstThred();
        });
        Thread thread2 = new Thread(() -> {
            runner.socondThred();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runner.finished();
    }
}

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void tackeLocks(Lock lock1, Lock lock2)  {
        boolean firstLockTacken = false;
        boolean secondLockTacken = false;
        while (true) {
            try {
                firstLockTacken = lock1.tryLock();
                secondLockTacken = lock2.tryLock();
            } finally {
                if (firstLockTacken && secondLockTacken) {
                    return;
                }
                if (firstLockTacken) {
                    lock1.unlock();
                }
                if (secondLockTacken) {
                    lock2.unlock();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void firstThred() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            tackeLocks(lock1,lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }

        }
    }

    public void socondThred() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
           tackeLocks(lock2,lock1);
            try {
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        System.out.println("Total: " + (account1.getBalance() + account2.getBalance()));
    }
}

class Account {
    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withrow(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account ac1, Account ac2, int amount) {
        ac1.withrow(amount);
        ac2.deposit(amount);
    }
}
