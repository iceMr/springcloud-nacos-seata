package com.example.thread;

public class TestTwo {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();
    private static volatile boolean Afinished = false;

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("A do something");
                    Afinished = true;
                    // 通知等待A资源的线程
                    resourceA.notify();
                }
                System.out.println("A do something end====");

            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    while (!Afinished) {
                        try {
                            // 让B线程在资源A上阻塞等待
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    synchronized (resourceB) {
                        System.out.println("B do something");
                        resourceB.notify();
                    }
                }

                System.out.println("B do something end====");
            }
        });
        threadA.start();
        threadB.start();
    }

}
