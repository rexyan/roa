package com.atguigu.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        // 三个停车位
        Semaphore semaphore = new Semaphore(3);

        // 每个线程一辆车
        for (int i = 1; i <=6 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取信号量
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " 进入停车场");
                        // 模拟车在里面停留
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 释放信号量
                        System.out.println(Thread.currentThread().getName() + " 开出停车场");
                        semaphore.release();
                    }
                }
            }, "车辆" + String.valueOf(i)).start();
        }
    }
}
