package com.atguigu.juc;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // CyclicBarrier 第一个参数是需要到达的次数，第二个是达到次数后调用的 Runnable 方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Runnable() {
            @Override
            public void run() {
                System.out.println("集齐七颗龙珠, 可以召唤神龙！！！");
            }
        });

        // 启动多个线程 (如果将这里的循环次数改为 6，那么是达不到 CyclicBarrier Runnable 方法执行的条件的)
        for (int i = 1; i <= 7 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "被收集");
                        // 如果未达到 CyclicBarrier 设置的计数，那么会被阻塞
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "龙珠" + String.valueOf(i)).start();
        }
    }
}
