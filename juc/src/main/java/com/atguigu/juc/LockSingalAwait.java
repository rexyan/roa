package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 四个线程 ABCD。两个线程进行 + 1。另外个线程进行 -1
 * 要求 +1 和 -1 交替进行。每个线程执行 10 次
 */
public class LockSingalAwait {
    private int number = 0;//初始值为零的一个变量
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number !=0 ) {
                condition.await();
            }
            //2干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

     public void decrement() throws InterruptedException {
         lock.lock();
         try {
             while (number !=1 ) {
                 condition.await();
             }
             //2干活
             number--;
             System.out.println(Thread.currentThread().getName()+"\t"+number);
             //3通知
             condition.signalAll();
         } finally {
             lock.unlock();
         }
    }

    public static void main(String[] args) {
        LockSingalAwait syncNotifyWait = new LockSingalAwait();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        syncNotifyWait.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        syncNotifyWait.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        syncNotifyWait.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程C").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        syncNotifyWait.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程D").start();
    }
}
