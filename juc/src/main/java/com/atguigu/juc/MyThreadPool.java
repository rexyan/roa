package com.atguigu.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,  // 核心线程数
                5,  // 最大线程数
                3L,  // 回收非核心线程的时间
                TimeUnit.SECONDS,  // 回收非核心线程的时间单位
                new ArrayBlockingQueue<Runnable>(3),  // 阻塞队列
                Executors.defaultThreadFactory(),  // 默认线程池工厂
                // new ThreadPoolExecutor.AbortPolicy()  // 拒绝策略
                // new ThreadPoolExecutor.CallerRunsPolicy()
                // new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardPolicy()
        );

        try{
            for (int i = 1; i <= 33 ; i++) {
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("线程：" + Thread.currentThread().getName() + "执行");
                    }
                });
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            threadPoolExecutor.shutdown();
        }
    }
}
