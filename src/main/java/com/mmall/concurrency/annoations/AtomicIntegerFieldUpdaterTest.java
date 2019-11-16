package com.mmall.concurrency.annoations;


import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

//核心是用原子性去更新某一个类的实例指定的某一个字段 该字段必须用volatile修饰，且不能用static修饰
public class AtomicIntegerFieldUpdaterTest {
    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class,"count");

    public volatile int count = 100;

    private static AtomicIntegerFieldUpdaterTest ex = new AtomicIntegerFieldUpdaterTest();


    public static void main(String[] args) {
        if(updater.compareAndSet(ex,100,120)){
            System.out.println("update success1 ");
        }
    }
}
