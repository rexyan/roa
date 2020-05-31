package com.atguigu.juc;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        //  void accept(T t);
        Consumer<String> consumer = t-> {
            System.out.println(t);
        };
        consumer.accept("ABC");  // ABC


        // T get();
        Supplier<String> supplier = () -> {
            return "DEF";
        };
        System.out.println(supplier.get());  // DEF


        //  R apply(T t);
        Function<String, Integer> function = (t) ->{
            return t.length();
        };
        System.out.println(function.apply("zhangsan"));  // 8


        // boolean test(T t);
        Predicate<String> predicate = (t) -> {
            return t.length() > 10?true:false;
        };
        System.out.println(predicate.test("zhangsan"));  // false
    }
}
