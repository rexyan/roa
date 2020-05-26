package com.atguigu.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ArrayListDemo {
    public static void main(String[] args) {
        // noSafeList();
        // noSafeSet();
        noSafeMap();

    }

    private static void noSafeMap() {
        // HashMap<String, Object> map = new HashMap<>(); //  线程不安全
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put(UUID.randomUUID().toString(), Thread.currentThread().getName());
                    System.out.println(map);
                }
            }, String.valueOf(i)).start();
        }
    }

    private static void noSafeSet() {
        // Set set = new HashSet();  // 线程不安全
        CopyOnWriteArraySet set = new CopyOnWriteArraySet(); //  线程安全，推荐使用

        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    set.add(UUID.randomUUID().toString());
                    System.out.println(set);
                }
            }, String.valueOf(i)).start();
        }
    }

    private static void noSafeList() {
        // List<String> list = new ArrayList<>();  // 线程不安全
        // List<String> list = new Vector<>();  // 线程安全，但是效率低下
        // List<String> list = Collections.synchronizedList(new ArrayList<>());  // 线程安全
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();  //  线程安全，推荐使用

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString());
                    System.out.println(list);
                }
            }, String.valueOf(i)).start();
        }
    }
}
