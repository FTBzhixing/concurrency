package com.mmall.concurrency.example.sync;

import com.mmall.concurrency.example.atomic.LongAddrTest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Example1 {

    public static Logger log = LoggerFactory.getLogger(LongAddrTest.class);
    //修饰一个代码块
    public void test1(){
        synchronized (this){
            for(int i = 0;i<10;i++){
                log.info("test1-{}",i);
            }
        }
    }
    //修饰一个方法，被修饰的方法叫做同步方法，作用范围是整个方法，作用对象也是整个方法
    //若有子类继承该类，则子类调用test2方法是没有synchronized关键字的
    public synchronized void test2(){
        for(int i = 0;i <10;i++){
            log.info("test2-{}",i);
        }
    }


    public static void main(String[] args) {
        Example1 example1 = new Example1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //作用于同一个对象，所以加上锁后一定是线程1执行完后再执行线程2
//        executorService.execute(() -> {
//            example1.test1();
//        });
//        executorService.execute(() -> {
//            example1.test1();
//        });

        Example1 example2 = new Example1();
        //当有两个对象在不同线程上操作时锁就没有作用了，线程1和线程2交叉执行
        executorService.execute(() -> {
            example1.test1();
        });
        executorService.execute(() -> {
            example2.test2();
        });
    }
}
