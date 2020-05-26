package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行！！");
                    // 让 CountDownLatch 的计数进行减 1 操作
                    countDownLatch.countDown();
                }
            }, "线程" + String.valueOf(i)).start();
        }
        // 阻塞
        countDownLatch.await();
        System.out.println("循环创建的线程全部执行完毕，我最后执行");
    }
}
