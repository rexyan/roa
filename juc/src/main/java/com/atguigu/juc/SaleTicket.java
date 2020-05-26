package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int number = 30;
    private Lock lock = new ReentrantLock();

    public void sale() {
        // 创建锁
        lock.lock();
        try {
            // 买票操作
            if(number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出：" + number-- + "号票，还剩：" + number + "张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        // 售票员 AA
        new Thread(()-> {for (int i = 0; i < 40; i++) ticket.sale();}, "AA").start();
        // 售票员 BB
        new Thread(()-> {for (int i = 0; i < 40; i++) ticket.sale();}, "BB").start();
        // 售票员 CC
        new Thread(()-> {for (int i = 0; i < 40; i++) ticket.sale();}, "CC").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "AA").start();
//
//        // 售票员 BB
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "BB").start();
//
//        // 售票员 CC
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "CC").start();
    }
}

