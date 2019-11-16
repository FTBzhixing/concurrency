package com.mmall.concurrency.example.sync;

import com.mmall.concurrency.example.atomic.LongAddrTest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Example2 {

    public static Logger log = LoggerFactory.getLogger(LongAddrTest.class);
    //修饰一个类
    public static void test1(){
        synchronized (Example2.class){
            for(int i = 0;i<10;i++){
                log.info("test1-{}",i);
            }
        }
    }
    //修饰一个静态方法
    public static synchronized void test2(){
        for(int i = 0;i <10;i++){
            log.info("test2-{}",i);
        }
    }


    public static void main(String[] args) {
        Example2 example1 = new Example2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //作用于同一个对象，所以加上锁后一定是线程1执行完后再执行线程2
//        executorService.execute(() -> {
//            example1.test1();
//        });
//        executorService.execute(() -> {
//            example1.test1();
//        });

        Example2 example2 = new Example2();
        //当有两个对象在不同线程上操作时锁就没有作用了，线程1和线程2交叉执行
        executorService.execute(() -> {
            example1.test1();
        });
        executorService.execute(() -> {
            example2.test2();
        });
    }
}
