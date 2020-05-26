package com.atguigu.juc;

public class LambdaDemo {
    @FunctionalInterface // 函数式接口（只能有一个实现的方法）
    interface Foo{
        public int add(int x, int y);

        // default 方法
        default int sub(int x, int y){
            return x - y;
        }
        default int div(int x, int y){
            return x / y;
        }

        // 静态方法
        public static int aa(int x, int y){
            return x + y + 100;
        }
        public static int bb(int x, int y){
            return x - y - 100;
        }
    }

    public static void main(String[] args) {
        // 实现方式1: 内部类实现
        Foo foo = new Foo() {
            @Override
            public int add(int x, int y) {
                return x + y;
            }
        };
        System.out.println(foo.add(10, 5));

        // 实现方式2: lambda 表达式实现（使用 lambda 的条件是必须是函数式接口，即只有一个实现的方法）
        Foo foo1 = (int x, int y) -> {
            return x + y;
        };
        System.out.println(foo1.add(10, 5));
        System.out.println(foo1.div(10, 5));
        System.out.println(foo1.sub(10, 5));
    }
}
