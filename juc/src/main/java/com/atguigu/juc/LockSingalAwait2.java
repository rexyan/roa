package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 需求：三个线程 AA，BB，CC，现要求，AA BB CC 按顺序执行
 * 且 AA 打印5次，BB 打印 10次，CC 打印 15 次，然后循环10次。
 */
public class LockSingalAwait2 {
    // 线程标志，1 表示该 AA 执行，2 BB 执行，3 CC 执行
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition(); // AA 线程的 Condition
    private Condition condition2 = lock.newCondition(); // BB 线程的 Condition
    private Condition condition3 = lock.newCondition(); // CC 线程的 Condition

    public void print5(int loopCount) throws InterruptedException {
        lock.lock();
        try {
            // 判断 (不等于1的时候，需要等待)
            while (number!=1){
                condition1.await();
            }
            // 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println("线程 :" + Thread.currentThread().getName() + " 打印：" + i + "次，当前第：" + loopCount + "轮");
            }

            // 通知 (修改标识位，并唤醒线程 BB)
            number = 2;
            condition2.signal();
        } finally {
            // 不管怎么样都要释放锁
            lock.unlock();
        }
    }

    public void print10(int loopCount) throws InterruptedException {
        lock.lock();
        try {
            // 判断 (不等于2的时候，需要等待)
            while (number!=2){
                condition2.await();
            }
            // 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println("线程 :" + Thread.currentThread().getName() + " 打印：" + i + "次，当前第：" + loopCount + "轮");
            }

            // 通知 (修改标识位，并唤醒线程 CC)
            number = 3;
            condition3.signal();
        } finally {
            // 不管怎么样都要释放锁
            lock.unlock();
        }
    }

    public void print15(int loopCount) throws InterruptedException {
        lock.lock();
        try {
            // 判断 (不等于2的时候，需要等待)
            while (number!=3){
                condition3.await();
            }
            // 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println("线程 :" + Thread.currentThread().getName() + " 打印：" + i + "次，当前第：" + loopCount + "轮");
            }

            // 通知 (修改标识位，并唤醒线程 AA【此处又回到 AA】)
            number = 1;
            condition1.signal();
        } finally {
            // 不管怎么样都要释放锁
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        LockSingalAwait2 lockSingalAwait2 = new LockSingalAwait2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    try {
                        lockSingalAwait2.print5(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    try {
                        lockSingalAwait2.print10(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    try {
                        lockSingalAwait2.print15(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "CC").start();
    }
}
