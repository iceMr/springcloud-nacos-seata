package com.example.thread;

import java.util.concurrent.CountDownLatch;

public class TestThree {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 do something");
                countDownLatch.countDown();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 do something");
                countDownLatch.countDown();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t3 do something");
                countDownLatch.countDown();
            }
        });

        t1.start();
        // 主线程阻塞等待子线程执行完毕
       countDownLatch.await();
        t2.start();
        // 主线程阻塞等待子线程
        countDownLatch.await();
        t3.start();
        // 主线程阻塞等待子线程
        countDownLatch.await();
    }

}
