package com.atguigu.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);

        /*
        // 抛出异常
        System.out.println(blockingQueue.add("a"));  // true
        System.out.println(blockingQueue.add("b"));  // true
        System.out.println(blockingQueue.add("c"));  // true
        // blockingQueue.add("d");  // java.lang.IllegalStateException: Queue full

        System.out.println(blockingQueue.remove());  // a
        System.out.println(blockingQueue.remove());  // b
        System.out.println(blockingQueue.remove());  // c
        // System.out.println(blockingQueue.remove()); // java.util.NoSuchElementException
        */

        /*
        // 特殊值
        System.out.println(blockingQueue.offer("a"));  // true
        System.out.println(blockingQueue.offer("b"));  // true
        System.out.println(blockingQueue.offer("c"));  // true
        // System.out.println(blockingQueue.offer("d"));  // false

        System.out.println(blockingQueue.poll());  // a
        System.out.println(blockingQueue.poll());  // b
        System.out.println(blockingQueue.poll());  // c
        // System.out.println(blockingQueue.poll());  // null
        */

        /*
        // 阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("b");
        // blockingQueue.put("d");  // 会导致阻塞

        System.out.println(blockingQueue.take());  // a
        System.out.println(blockingQueue.take());  // b
        System.out.println(blockingQueue.take());  // c
        // System.out.println(blockingQueue.take());  // 会导致阻塞
        */

        // 超时
        System.out.println(blockingQueue.offer("a", 4, TimeUnit.SECONDS));  // true
        System.out.println(blockingQueue.offer("b", 4, TimeUnit.SECONDS));  // true
        System.out.println(blockingQueue.offer("c", 4, TimeUnit.SECONDS));  // true
        // System.out.println(blockingQueue.offer("d", 4, TimeUnit.SECONDS));  // 超过4s返回 false

        System.out.println(blockingQueue.poll(4, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(4, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(4, TimeUnit.SECONDS));
        // System.out.println(blockingQueue.poll(4, TimeUnit.SECONDS));  // 超过4s返回 null
    }
}
