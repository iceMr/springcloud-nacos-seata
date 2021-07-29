package com.example.thread;

public class TestOne {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 do something");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 do something");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t3 do something");
            }
        });
        t1.start();
        // 主线程阻塞等待子线程执行完
        t1.join();
        t2.start();
        // 主线程阻塞等待子线程执行完
        t2.join();
        t3.start();
        // 主线程阻塞等待子线程执行完
        t3.join();
    }

}
