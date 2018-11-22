package com.sham.demo.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {

    ThreadLocal<String>  threadLocal=new ThreadLocal<>();
    public void method(Thread thread) {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println(thread.getName() + "获得锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(thread.getName() + "释放锁");
            lock.unlock();
        }


    }

    public static void main(String[] args) {


        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Demo demo = new Demo();
                    demo.method(Thread.currentThread());
                }
            }).start();
        }


    }
}
